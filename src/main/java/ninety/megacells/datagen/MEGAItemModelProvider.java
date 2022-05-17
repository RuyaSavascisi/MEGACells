package ninety.megacells.datagen;

import java.util.stream.Stream;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import appeng.core.AppEng;

import ninety.megacells.MEGACells;
import ninety.megacells.integration.appmek.ChemicalCellType;
import ninety.megacells.item.MEGAItems;
import ninety.megacells.item.util.MEGACellTier;
import ninety.megacells.item.util.MEGACellType;

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
        flatSingleLayer(MEGAItems.MEGA_CHEMICAL_CELL_HOUSING.asItem());

        for (var tier : MEGACellTier.values()) {
            flatSingleLayer(tier.getComponent());
        }

        for (var storage : Stream.of(
                MEGACellType.ITEM.getCells().stream(),
                MEGACellType.FLUID.getCells().stream(),
                ChemicalCellType.TYPE.getCells().stream()).flatMap(s -> s).toList()) {
            cell(storage);
        }
        for (var portable : Stream.of(
                MEGACellType.ITEM.getPortableCells().stream(),
                MEGACellType.FLUID.getPortableCells().stream(),
                ChemicalCellType.TYPE.getPortableCells().stream()).flatMap(s -> s).toList()) {
            portable(portable);
        }
    }

    private void cell(Item cell) {
        flatSingleLayer(cell, "/cells/standard").texture("layer1", STORAGE_CELL_LED);
    }

    private void portable(Item cell) {
        flatSingleLayer(cell, "/cells/portable").texture("layer1", PORTABLE_CELL_LED);
    }

    private ItemModelBuilder flatSingleLayer(Item item) {
        return flatSingleLayer(item, "");
    }

    private ItemModelBuilder flatSingleLayer(Item item, String subfolder) {
        String path = MEGAItems.getItemPath(item);
        return singleTexture(path, mcLoc("item/generated"), "layer0",
                MEGACells.makeId("item" + subfolder + "/" + path));
    }
}
