package gripe._90.megacells.integration.appmek;

import java.util.Objects;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import appeng.api.config.Actionable;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.KeyCounter;
import appeng.api.storage.cells.CellState;
import appeng.api.storage.cells.ISaveProvider;
import appeng.api.storage.cells.StorageCell;

import me.ramidzkh.mekae2.ae2.MekanismKey;
import me.ramidzkh.mekae2.ae2.MekanismKeyType;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.common.registries.MekanismChemicals;

import gripe._90.megacells.definition.MEGAComponents;
import gripe._90.megacells.definition.MEGAConfig;

public class RadioactiveCellInventory implements StorageCell {
    static final int MAX_BYTES = 256;
    private static final int MAX_MB = MAX_BYTES * MekanismKeyType.TYPE.getAmountPerByte();

    private final ISaveProvider container;
    private final ItemStack stack;

    private AEKey storedChemical;
    private final MekanismKey filterChemical;
    private long chemAmount;
    private boolean isPersisted = true;

    RadioactiveCellInventory(ItemStack stack, ISaveProvider container) {
        this.stack = stack;
        this.container = container;

        var cell = (RadioactiveCellItem) stack.getItem();
        var filter = cell.getConfigInventory(this.stack).getKey(0);
        filterChemical = filter instanceof MekanismKey chemical ? chemical : null;

        storedChemical = stack.get(MEGAComponents.RADIOACTIVE_CELL_CHEMICAL);
        chemAmount = stack.getOrDefault(MEGAComponents.RADIOACTIVE_CELL_AMOUNT, 0L);
    }

    @Override
    public CellState getStatus() {
        if (chemAmount == 0) {
            return CellState.EMPTY;
        }

        if (chemAmount == MAX_MB) {
            return CellState.FULL;
        }

        if (chemAmount > MAX_MB / 2) {
            return CellState.TYPES_FULL;
        }

        if (!storedChemical.equals(getFilterChemical())) {
            return CellState.FULL;
        }

        return CellState.NOT_EMPTY;
    }

    public AEKey getStoredChemical() {
        return storedChemical;
    }

    public long getChemAmount() {
        return chemAmount;
    }

    public AEKey getFilterChemical() {
        return filterChemical;
    }

    public long getUsedBytes() {
        return chemAmount / MekanismKeyType.TYPE.getAmountPerByte();
    }

    @Override
    public double getIdleDrain() {
        return 250.0f;
    }

    public boolean isBlackListed(AEKey what) {
        return !(what instanceof MekanismKey key)
                || (key.getStack().getChemical() == MekanismChemicals.SPENT_NUCLEAR_WASTE.get()
                        ? !MEGAConfig.CONFIG.isSpentWasteAllowed()
                        : ChemicalAttributeValidator.DEFAULT.process(key.getStack()));
    }

    @Override
    public long insert(AEKey what, long amount, Actionable mode, IActionSource source) {
        if (amount == 0 || !MekanismKeyType.TYPE.contains(what)) {
            return 0;
        }

        if (!what.equals(filterChemical) || isBlackListed(what)) {
            return 0;
        }

        if (storedChemical != null && !storedChemical.equals(what)) {
            return 0;
        }

        if (chemAmount == MAX_MB) {
            return 0;
        }

        long remainingAmount = Math.max(0, MAX_MB - chemAmount);
        if (amount > remainingAmount) {
            amount = remainingAmount;
        }

        if (mode == Actionable.MODULATE) {
            if (storedChemical == null) {
                storedChemical = what;
            }

            chemAmount += amount;
            saveChanges();
        }

        return amount;
    }

    @Override
    public long extract(AEKey what, long amount, Actionable mode, IActionSource source) {
        var extractAmount = Math.min(Integer.MAX_VALUE, amount);

        var currentCount = this.chemAmount;
        if (chemAmount > 0 && Objects.equals(storedChemical, what)) {
            if (extractAmount >= currentCount) {
                if (mode == Actionable.MODULATE) {
                    storedChemical = null;
                    chemAmount = 0;
                    saveChanges();
                }

                return currentCount;
            } else {
                if (mode == Actionable.MODULATE) {
                    chemAmount -= extractAmount;
                    saveChanges();
                }

                return extractAmount;
            }
        }
        return 0;
    }

    protected void saveChanges() {
        isPersisted = false;

        if (container != null) {
            container.saveChanges();
        } else {
            persist();
        }
    }

    @Override
    public void persist() {
        if (isPersisted) {
            return;
        }

        if (storedChemical == null || chemAmount < 0) {
            stack.remove(MEGAComponents.RADIOACTIVE_CELL_CHEMICAL);
            stack.remove(MEGAComponents.RADIOACTIVE_CELL_AMOUNT);
        } else {
            stack.set(MEGAComponents.RADIOACTIVE_CELL_CHEMICAL, storedChemical);
            stack.set(MEGAComponents.RADIOACTIVE_CELL_AMOUNT, chemAmount);
        }

        isPersisted = true;
    }

    @Override
    public void getAvailableStacks(KeyCounter out) {
        if (storedChemical != null && chemAmount > 0) {
            out.add(storedChemical, chemAmount);
        }
    }

    @Override
    public boolean canFitInsideCell() {
        return filterChemical == null && storedChemical == null && chemAmount == 0;
    }

    @Override
    public Component getDescription() {
        return stack.getHoverName();
    }
}
