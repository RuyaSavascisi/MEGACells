package gripe._90.megacells.definition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import appeng.api.parts.PartModels;
import appeng.api.stacks.AEKeyType;
import appeng.core.definitions.ItemDefinition;
import appeng.items.materials.EnergyCardItem;
import appeng.items.materials.MaterialItem;
import appeng.items.materials.StorageComponentItem;
import appeng.items.materials.UpgradeCardItem;
import appeng.items.parts.PartItem;
import appeng.items.parts.PartModelsHelper;
import appeng.items.storage.BasicStorageCell;
import appeng.items.storage.StorageTier;
import appeng.menu.me.common.MEStorageMenu;

import gripe._90.megacells.MEGACells;
import gripe._90.megacells.integration.Addons;
import gripe._90.megacells.integration.DummyIntegrationItem;
import gripe._90.megacells.integration.appliede.MEGAEMCInterfacePart;
import gripe._90.megacells.integration.appmek.RadioactiveCellItem;
import gripe._90.megacells.item.cell.BulkCellItem;
import gripe._90.megacells.item.cell.MEGAPortableCell;
import gripe._90.megacells.item.cell.PortableCellWorkbenchItem;
import gripe._90.megacells.item.part.CellDockPart;
import gripe._90.megacells.item.part.DecompressionModulePart;
import gripe._90.megacells.item.part.MEGAInterfacePart;
import gripe._90.megacells.item.part.MEGAPatternProviderPart;
import gripe._90.megacells.item.part.MEGAPatternProviderPartItem;

public final class MEGAItems {
    public static final DeferredRegister.Items DR = DeferredRegister.createItems(MEGACells.MODID);

    private static final List<ItemDefinition<?>> ITEMS = new ArrayList<>();
    private static final List<CellDefinition> CELLS = new ArrayList<>();

    public static List<ItemDefinition<?>> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }

    public static List<CellDefinition> getTieredCells() {
        return Collections.unmodifiableList(CELLS);
    }

    // spotless:off
    public static final ItemDefinition<MaterialItem> SKY_STEEL_INGOT = item("Sky Steel Ingot", "sky_steel_ingot", p -> new MaterialItem(p.fireResistant()));
    public static final ItemDefinition<MaterialItem> SKY_BRONZE_INGOT = item("Sky Bronze Ingot", "sky_bronze_ingot", p -> new MaterialItem(p.fireResistant()));

    public static final ItemDefinition<MaterialItem> ACCUMULATION_PROCESSOR_PRESS = item("Inscriber Accumulation Press", "accumulation_processor_press", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> ACCUMULATION_PROCESSOR_PRINT = item("Printed Accumulation Circuit", "printed_accumulation_processor", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> ACCUMULATION_PROCESSOR = item("Accumulation Processor", "accumulation_processor", MaterialItem::new);

    public static final ItemDefinition<MaterialItem> MEGA_ITEM_CELL_HOUSING = item("MEGA Item Cell Housing", "mega_item_cell_housing", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> MEGA_FLUID_CELL_HOUSING = item("MEGA Fluid Cell Housing", "mega_fluid_cell_housing", MaterialItem::new);

    public static final ItemDefinition<StorageComponentItem> CELL_COMPONENT_1M = component(1);
    public static final ItemDefinition<StorageComponentItem> CELL_COMPONENT_4M = component(4);
    public static final ItemDefinition<StorageComponentItem> CELL_COMPONENT_16M = component(16);
    public static final ItemDefinition<StorageComponentItem> CELL_COMPONENT_64M = component(64);
    public static final ItemDefinition<StorageComponentItem> CELL_COMPONENT_256M = component(256);

    public static final StorageTier TIER_1M = tier(6, CELL_COMPONENT_1M);
    public static final StorageTier TIER_4M = tier(7, CELL_COMPONENT_4M);
    public static final StorageTier TIER_16M = tier(8, CELL_COMPONENT_16M);
    public static final StorageTier TIER_64M = tier(9, CELL_COMPONENT_64M);
    public static final StorageTier TIER_256M = tier(10, CELL_COMPONENT_256M);

    public static final ItemDefinition<BasicStorageCell> ITEM_CELL_1M = itemCell(TIER_1M);
    public static final ItemDefinition<BasicStorageCell> ITEM_CELL_4M = itemCell(TIER_4M);
    public static final ItemDefinition<BasicStorageCell> ITEM_CELL_16M = itemCell(TIER_16M);
    public static final ItemDefinition<BasicStorageCell> ITEM_CELL_64M = itemCell(TIER_64M);
    public static final ItemDefinition<BasicStorageCell> ITEM_CELL_256M = itemCell(TIER_256M);

    public static final ItemDefinition<BasicStorageCell> FLUID_CELL_1M = fluidCell(TIER_1M);
    public static final ItemDefinition<BasicStorageCell> FLUID_CELL_4M = fluidCell(TIER_4M);
    public static final ItemDefinition<BasicStorageCell> FLUID_CELL_16M = fluidCell(TIER_16M);
    public static final ItemDefinition<BasicStorageCell> FLUID_CELL_64M = fluidCell(TIER_64M);
    public static final ItemDefinition<BasicStorageCell> FLUID_CELL_256M = fluidCell(TIER_256M);

    public static final ItemDefinition<MEGAPortableCell> PORTABLE_ITEM_CELL_1M = itemPortable(TIER_1M);
    public static final ItemDefinition<MEGAPortableCell> PORTABLE_ITEM_CELL_4M = itemPortable(TIER_4M);
    public static final ItemDefinition<MEGAPortableCell> PORTABLE_ITEM_CELL_16M = itemPortable(TIER_16M);
    public static final ItemDefinition<MEGAPortableCell> PORTABLE_ITEM_CELL_64M = itemPortable(TIER_64M);
    public static final ItemDefinition<MEGAPortableCell> PORTABLE_ITEM_CELL_256M = itemPortable(TIER_256M);

    public static final ItemDefinition<MEGAPortableCell> PORTABLE_FLUID_CELL_1M = fluidPortable(TIER_1M);
    public static final ItemDefinition<MEGAPortableCell> PORTABLE_FLUID_CELL_4M = fluidPortable(TIER_4M);
    public static final ItemDefinition<MEGAPortableCell> PORTABLE_FLUID_CELL_16M = fluidPortable(TIER_16M);
    public static final ItemDefinition<MEGAPortableCell> PORTABLE_FLUID_CELL_64M = fluidPortable(TIER_64M);
    public static final ItemDefinition<MEGAPortableCell> PORTABLE_FLUID_CELL_256M = fluidPortable(TIER_256M);

    public static final ItemDefinition<EnergyCardItem> GREATER_ENERGY_CARD = item("Greater Energy Card", "greater_energy_card", p -> new EnergyCardItem(p, 8));

    public static final ItemDefinition<MaterialItem> BULK_CELL_COMPONENT = item("MEGA Bulk Storage Component", "bulk_cell_component", MaterialItem::new);
    public static final ItemDefinition<BulkCellItem> BULK_ITEM_CELL = item("MEGA Bulk Item Storage Cell", "bulk_item_cell", BulkCellItem::new);
    public static final ItemDefinition<UpgradeCardItem> COMPRESSION_CARD = item("Compression Card", "compression_card", UpgradeCardItem::new);
    public static final ItemDefinition<PartItem<DecompressionModulePart>> DECOMPRESSION_MODULE = part("MEGA Decompression Module", "decompression_module", DecompressionModulePart.class, DecompressionModulePart::new);

    public static final ItemDefinition<PartItem<MEGAInterfacePart>> MEGA_INTERFACE = part("MEGA Interface", "cable_mega_interface", MEGAInterfacePart.class, MEGAInterfacePart::new);
    public static final ItemDefinition<MEGAPatternProviderPartItem> MEGA_PATTERN_PROVIDER = Util.make(() -> {
        PartModels.registerModels(PartModelsHelper.createModels(MEGAPatternProviderPart.class));
        return item("MEGA Pattern Provider", "cable_mega_pattern_provider", MEGAPatternProviderPartItem::new);
    });

    public static final ItemDefinition<PartItem<CellDockPart>> CELL_DOCK = part("ME Cell Dock", "cell_dock", CellDockPart.class, CellDockPart::new);
    public static final ItemDefinition<PortableCellWorkbenchItem> PORTABLE_CELL_WORKBENCH = item("Portable Cell Workbench", "portable_cell_workbench", PortableCellWorkbenchItem::new);

    public static final ItemDefinition<?> SKY_OSMIUM_INGOT = integrationItem("Sky Osmium Ingot", "sky_osmium_ingot", () -> MaterialItem::new, Item.Properties::fireResistant, Addons.APPMEK);
    public static final ItemDefinition<?> MEGA_CHEMICAL_CELL_HOUSING = integrationItem("MEGA Chemical Cell Housing", "mega_chemical_cell_housing", () -> MaterialItem::new, Addons.APPMEK);

    public static final ItemDefinition<?> CHEMICAL_CELL_1M = integrationCell(TIER_1M, "Chemical", Addons.APPMEK);
    public static final ItemDefinition<?> CHEMICAL_CELL_4M = integrationCell(TIER_4M, "Chemical", Addons.APPMEK);
    public static final ItemDefinition<?> CHEMICAL_CELL_16M = integrationCell(TIER_16M, "Chemical", Addons.APPMEK);
    public static final ItemDefinition<?> CHEMICAL_CELL_64M = integrationCell(TIER_64M, "Chemical", Addons.APPMEK);
    public static final ItemDefinition<?> CHEMICAL_CELL_256M = integrationCell(TIER_256M, "Chemical", Addons.APPMEK);

    public static final ItemDefinition<?> PORTABLE_CHEMICAL_CELL_1M = integrationPortable(TIER_1M, "Chemical", Addons.APPMEK);
    public static final ItemDefinition<?> PORTABLE_CHEMICAL_CELL_4M = integrationPortable(TIER_4M, "Chemical", Addons.APPMEK);
    public static final ItemDefinition<?> PORTABLE_CHEMICAL_CELL_16M = integrationPortable(TIER_16M, "Chemical", Addons.APPMEK);
    public static final ItemDefinition<?> PORTABLE_CHEMICAL_CELL_64M = integrationPortable(TIER_64M, "Chemical", Addons.APPMEK);
    public static final ItemDefinition<?> PORTABLE_CHEMICAL_CELL_256M = integrationPortable(TIER_256M, "Chemical", Addons.APPMEK);

    public static final ItemDefinition<?> RADIOACTIVE_CELL_COMPONENT = integrationItem("MEGA Radioactive Storage Component", "radioactive_cell_component", () -> MaterialItem::new, Addons.APPMEK);
    public static final ItemDefinition<?> RADIOACTIVE_CHEMICAL_CELL = integrationItem("MEGA Radioactive Chemical Storage Cell", "radioactive_chemical_cell", () -> RadioactiveCellItem::new, Addons.APPMEK);

    public static final ItemDefinition<?> MEGA_MANA_CELL_HOUSING = integrationItem("MEGA Mana Cell Housing", "mega_mana_cell_housing", () -> MaterialItem::new, Addons.APPBOT);

    public static final ItemDefinition<?> MANA_CELL_1M = integrationCell(TIER_1M, "Mana", Addons.APPBOT);
    public static final ItemDefinition<?> MANA_CELL_4M = integrationCell(TIER_4M, "Mana", Addons.APPBOT);
    public static final ItemDefinition<?> MANA_CELL_16M = integrationCell(TIER_16M, "Mana", Addons.APPBOT);
    public static final ItemDefinition<?> MANA_CELL_64M = integrationCell(TIER_64M, "Mana", Addons.APPBOT);
    public static final ItemDefinition<?> MANA_CELL_256M = integrationCell(TIER_256M, "Mana", Addons.APPBOT);

    public static final ItemDefinition<?> PORTABLE_MANA_CELL_1M = integrationPortable(TIER_1M, "Mana", Addons.APPBOT);
    public static final ItemDefinition<?> PORTABLE_MANA_CELL_4M = integrationPortable(TIER_4M, "Mana", Addons.APPBOT);
    public static final ItemDefinition<?> PORTABLE_MANA_CELL_16M = integrationPortable(TIER_16M, "Mana", Addons.APPBOT);
    public static final ItemDefinition<?> PORTABLE_MANA_CELL_64M = integrationPortable(TIER_64M, "Mana", Addons.APPBOT);
    public static final ItemDefinition<?> PORTABLE_MANA_CELL_256M = integrationPortable(TIER_256M, "Mana", Addons.APPBOT);

    public static final ItemDefinition<?> MEGA_SOURCE_CELL_HOUSING = integrationItem("MEGA Source Cell Housing", "mega_source_cell_housing", () -> MaterialItem::new, Addons.ARSENG);

    public static final ItemDefinition<?> SOURCE_CELL_1M = integrationCell(TIER_1M, "Source", Addons.ARSENG);
    public static final ItemDefinition<?> SOURCE_CELL_4M = integrationCell(TIER_4M, "Source", Addons.ARSENG);
    public static final ItemDefinition<?> SOURCE_CELL_16M = integrationCell(TIER_16M, "Source", Addons.ARSENG);
    public static final ItemDefinition<?> SOURCE_CELL_64M = integrationCell(TIER_64M, "Source", Addons.ARSENG);
    public static final ItemDefinition<?> SOURCE_CELL_256M = integrationCell(TIER_256M, "Source", Addons.ARSENG);

    public static final ItemDefinition<?> PORTABLE_SOURCE_CELL_1M = integrationPortable(TIER_1M, "Source", Addons.ARSENG);
    public static final ItemDefinition<?> PORTABLE_SOURCE_CELL_4M = integrationPortable(TIER_4M, "Source", Addons.ARSENG);
    public static final ItemDefinition<?> PORTABLE_SOURCE_CELL_16M = integrationPortable(TIER_16M, "Source", Addons.ARSENG);
    public static final ItemDefinition<?> PORTABLE_SOURCE_CELL_64M = integrationPortable(TIER_64M, "Source", Addons.ARSENG);
    public static final ItemDefinition<?> PORTABLE_SOURCE_CELL_256M = integrationPortable(TIER_256M, "Source", Addons.ARSENG);

    public static final ItemDefinition<?> MEGA_EXPERIENCE_CELL_HOUSING = integrationItem("MEGA Experience Cell Housing", "mega_experience_cell_housing", () -> MaterialItem::new, Addons.APPEX);

    public static final ItemDefinition<?> EXPERIENCE_CELL_1M = integrationCell(TIER_1M, "Experience", Addons.APPEX);
    public static final ItemDefinition<?> EXPERIENCE_CELL_4M = integrationCell(TIER_4M, "Experience", Addons.APPEX);
    public static final ItemDefinition<?> EXPERIENCE_CELL_16M = integrationCell(TIER_16M, "Experience", Addons.APPEX);
    public static final ItemDefinition<?> EXPERIENCE_CELL_64M = integrationCell(TIER_64M, "Experience", Addons.APPEX);
    public static final ItemDefinition<?> EXPERIENCE_CELL_256M = integrationCell(TIER_256M, "Experience", Addons.APPEX);

    public static final ItemDefinition<?> PORTABLE_EXPERIENCE_CELL_1M = integrationPortable(TIER_1M, "Experience", Addons.APPEX);
    public static final ItemDefinition<?> PORTABLE_EXPERIENCE_CELL_4M = integrationPortable(TIER_4M, "Experience", Addons.APPEX);
    public static final ItemDefinition<?> PORTABLE_EXPERIENCE_CELL_16M = integrationPortable(TIER_16M, "Experience", Addons.APPEX);
    public static final ItemDefinition<?> PORTABLE_EXPERIENCE_CELL_64M = integrationPortable(TIER_64M, "Experience", Addons.APPEX);
    public static final ItemDefinition<?> PORTABLE_EXPERIENCE_CELL_256M = integrationPortable(TIER_256M, "Experience", Addons.APPEX);

    public static final ItemDefinition<?> MEGA_EMC_INTERFACE = integrationItem("MEGA Transmutation Interface", "cable_mega_emc_interface", () -> p -> {
        PartModels.registerModels(PartModelsHelper.createModels(MEGAEMCInterfacePart.class));
        return new PartItem<>(p, MEGAEMCInterfacePart.class, MEGAEMCInterfacePart::new);
    }, Addons.APPLIEDE);
    // spotless:on

    private static StorageTier tier(int index, ItemDefinition<StorageComponentItem> component) {
        int multiplier = (int) Math.pow(4, index - 1);
        return new StorageTier(index, (multiplier / 1024) + "m", 1024 * multiplier, 0.5 * index, component::asItem);
    }

    private static ItemDefinition<StorageComponentItem> component(int mb) {
        return item(
                mb + "M MEGA Storage Component",
                "cell_component_" + mb + "m",
                p -> new StorageComponentItem(p, mb * 1024));
    }

    private static ItemDefinition<BasicStorageCell> itemCell(StorageTier tier) {
        var cell = item(
                tier.namePrefix().toUpperCase() + " MEGA Item Storage Cell",
                "item_storage_cell_" + tier.namePrefix(),
                p -> new BasicStorageCell(
                        p.stacksTo(1),
                        tier.idleDrain(),
                        tier.bytes() / 1024,
                        tier.bytes() / 128,
                        63,
                        AEKeyType.items()));
        CELLS.add(new CellDefinition(cell, tier, "item", false));
        return cell;
    }

    private static ItemDefinition<BasicStorageCell> fluidCell(StorageTier tier) {
        var cell = item(
                tier.namePrefix().toUpperCase() + " MEGA Fluid Storage Cell",
                "fluid_storage_cell_" + tier.namePrefix(),
                p -> new BasicStorageCell(
                        p.stacksTo(1),
                        tier.idleDrain(),
                        tier.bytes() / 1024,
                        tier.bytes() / 128,
                        18,
                        AEKeyType.fluids()));
        CELLS.add(new CellDefinition(cell, tier, "fluid", false));
        return cell;
    }

    private static ItemDefinition<?> integrationCell(StorageTier tier, String type, Addons addon) {
        var cell = integrationItem(
                tier.namePrefix().toUpperCase() + " MEGA " + type + " Storage Cell",
                type.toLowerCase() + "_storage_cell_" + tier.namePrefix(),
                () -> addon.getHelper().createCell(tier),
                props -> props.stacksTo(1),
                addon);
        CELLS.add(new CellDefinition(cell, tier, type.toLowerCase(), false));
        return cell;
    }

    private static ItemDefinition<MEGAPortableCell> itemPortable(StorageTier tier) {
        var cell = item(
                tier.namePrefix().toUpperCase() + " Portable Item Cell",
                "portable_item_cell_" + tier.namePrefix(),
                p -> new MEGAPortableCell(p, tier, AEKeyType.items(), MEStorageMenu.PORTABLE_ITEM_CELL_TYPE, 0x80caff));
        CELLS.add(new CellDefinition(cell, tier, "item", true));
        return cell;
    }

    private static ItemDefinition<MEGAPortableCell> fluidPortable(StorageTier tier) {
        var cell = item(
                tier.namePrefix().toUpperCase() + " Portable Fluid Cell",
                "portable_fluid_cell_" + tier.namePrefix(),
                p -> new MEGAPortableCell(
                        p, tier, AEKeyType.fluids(), MEStorageMenu.PORTABLE_FLUID_CELL_TYPE, 0x80caff));
        CELLS.add(new CellDefinition(cell, tier, "fluid", true));
        return cell;
    }

    private static ItemDefinition<?> integrationPortable(StorageTier tier, String type, Addons addon) {
        var cell = integrationItem(
                tier.namePrefix().toUpperCase() + " Portable " + type + " Cell",
                "portable_" + type.toLowerCase() + "_cell_" + tier.namePrefix(),
                () -> addon.getHelper().createPortable(tier),
                props -> props.stacksTo(1),
                addon);
        CELLS.add(new CellDefinition(cell, tier, type.toLowerCase(), true));
        return cell;
    }

    private static <T extends IPart> ItemDefinition<PartItem<T>> part(
            String englishName, String id, Class<T> partClass, Function<IPartItem<T>, T> factory) {
        PartModels.registerModels(PartModelsHelper.createModels(partClass));
        return item(englishName, id, p -> new PartItem<>(p, partClass, factory));
    }

    private static <T extends Item> ItemDefinition<T> item(
            String englishName, String id, Function<Item.Properties, T> factory) {
        var definition = new ItemDefinition<>(englishName, DR.registerItem(id, factory));
        ITEMS.add(definition);
        return definition;
    }

    private static ItemDefinition<?> integrationItem(
            String englishName,
            String id,
            Supplier<Function<Item.Properties, Item>> factory,
            Function<Item.Properties, Item.Properties> propsCustomizer,
            Addons addon) {
        return item(
                englishName,
                id,
                p -> addon.isLoaded()
                        ? factory.get().apply(propsCustomizer.apply(p))
                        : new DummyIntegrationItem(propsCustomizer.apply(p), addon));
    }

    private static ItemDefinition<?> integrationItem(
            String englishName, String id, Supplier<Function<Item.Properties, Item>> factory, Addons addon) {
        return integrationItem(englishName, id, factory, p -> p, addon);
    }

    public record CellDefinition(ItemDefinition<?> item, StorageTier tier, String keyType, boolean portable) {}
}
