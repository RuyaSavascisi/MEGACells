package gripe._90.megacells.datagen;

import java.util.stream.Stream;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import appeng.core.AppEng;

import gripe._90.megacells.MEGACells;
import gripe._90.megacells.integration.appbot.AppBotCellType;
import gripe._90.megacells.integration.appbot.AppBotItems;
import gripe._90.megacells.item.MEGAItems;
import gripe._90.megacells.item.core.MEGACellType;
import gripe._90.megacells.item.core.MEGATier;

public class MEGAItemModelProvider extends ItemModelProvider {

    static final ResourceLocation STORAGE_CELL_LED = AppEng.makeId("item/storage_cell_led");
    static final ResourceLocation PORTABLE_CELL_LED = AppEng.makeId("item/portable_cell_led");

    public MEGAItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MEGACells.MODID, existingFileHelper);
        existingFileHelper.trackGenerated(STORAGE_CELL_LED, TEXTURE);
        existingFileHelper.trackGenerated(PORTABLE_CELL_LED, TEXTURE);
    }

    @Override
    protected void registerModels() {
        flatSingleLayer(MEGAItems.MEGA_ITEM_CELL_HOUSING.asItem());
        flatSingleLayer(MEGAItems.MEGA_FLUID_CELL_HOUSING.asItem());

        flatSingleLayer(AppBotItems.MEGA_MANA_CELL_HOUSING.asItem());

        for (var tier : MEGATier.values()) {
            flatSingleLayer(tier.getComponent());
        }

        for (var storage : Stream.of(
                MEGACellType.ITEM.getCells().stream(),
                MEGACellType.FLUID.getCells().stream(),
                AppBotCellType.MANA.getCells().stream()).flatMap(s -> s).toList()) {
            cell(storage);
        }

        flatSingleLayer(MEGAItems.BULK_CELL_COMPONENT.asItem());
        cell(MEGAItems.BULK_ITEM_CELL.asItem());

        for (var portable : Stream.of(
                MEGACellType.ITEM.getPortableCells().stream(),
                MEGACellType.FLUID.getPortableCells().stream(),
                AppBotCellType.MANA.getPortableCells().stream()).flatMap(s -> s).toList()) {
            portable(portable);
        }
    }

    private void cell(Item cell) {
        flatSingleLayer(cell, "/cells/standard").texture("layer1", STORAGE_CELL_LED);
    }

    private void portable(Item cell) {
        flatSingleLayer(cell, "/cells/portable").texture("layer1", PORTABLE_CELL_LED);
    }

    private void flatSingleLayer(Item item) {
        flatSingleLayer(item, "");
    }

    private ItemModelBuilder flatSingleLayer(Item item, String subfolder) {
        String path = MEGACells.getItemPath(item);
        return singleTexture(path, mcLoc("item/generated"), "layer0",
                MEGACells.makeId("item" + subfolder + "/" + path));
    }
}
