package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.common.tileentity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMTileEntity {
    public static DeferredRegister<BlockEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DocMod.MOD_ID);

    public static RegistryObject<BlockEntityType<ToyotaRotorTileEntity>> Toyota =
            TILE_ENTITIES.register("toyota", () -> BlockEntityType.Builder.of(
                    ToyotaRotorTileEntity::new, DMBlocks.Toyota.get()).build(null));

    public static RegistryObject<BlockEntityType<GlassTubeTile>> GlassTile =
            TILE_ENTITIES.register("glasstile", () -> BlockEntityType.Builder.of(
                    GlassTubeTile::new, DMBlocks.GLASS_TUBE.get()).build(null));

    public static RegistryObject<BlockEntityType<TardisTileEntity>> Tardis =
            TILE_ENTITIES.register("tardistile", () -> BlockEntityType.Builder.of(
                    TardisTileEntity::new, DMBlocks.Tardis.get()).build(null));

    public static RegistryObject<BlockEntityType<be.ninedocteur.docmod.common.tileentity.ZurbTeleporterTile>> ZurbTeleporterTile =
            TILE_ENTITIES.register("zurbteleportertile", () -> BlockEntityType.Builder.of(
                    ZurbTeleporterTile::new, DMBlocks.ZURBTELEPORTER.get()).build(null));

    public static RegistryObject<BlockEntityType<RedGlassTubeTile>> RedGlassTile =
            TILE_ENTITIES.register("redglasstile", () -> BlockEntityType.Builder.of(
                    RedGlassTubeTile::new, DMBlocks.RED_GLASS_TUBE.get()).build(null));

    public static RegistryObject<BlockEntityType<GreenGlassTubeTile>> GreenGlassTile =
            TILE_ENTITIES.register("greenglasstile", () -> BlockEntityType.Builder.of(
                    GreenGlassTubeTile::new, DMBlocks.GREEN_GLASS_TUBE.get()).build(null));

    public static RegistryObject<BlockEntityType<OrangeGlassTubeTile>> OrangeGlassTile =
            TILE_ENTITIES.register("orangeglasstile", () -> BlockEntityType.Builder.of(
                    OrangeGlassTubeTile::new, DMBlocks.ORANGE_GLASS_TUBE.get()).build(null));

   public static RegistryObject<BlockEntityType<DeathSignTile>> DeathSign =
            TILE_ENTITIES.register("death_sign", () -> BlockEntityType.Builder.of(
                    DeathSignTile::new, DMBlocks.DEATH_SIGN.get(), DMBlocks.DEATH_WALL_SIGN.get()).build(null));

   public static RegistryObject<BlockEntityType<AlbiziaSignTile>> AlbiziaSign =
            TILE_ENTITIES.register("albizia_sign", () -> BlockEntityType.Builder.of(
                    AlbiziaSignTile::new, DMBlocks.ALBIZIA_SIGN.get(), DMBlocks.ALBIZIA_WALL_SIGN.get()).build(null));
/*
    public static RegistryObject<BlockEntityType<ChairTileEntity>> ChairTile =
            TILE_ENTITIES.register("chair_tile", () -> BlockEntityType.Builder.of(
                    ChairTileEntity::new, DMBlocks.CHAIR.get()).build(null));

 */

    public static RegistryObject<BlockEntityType<PandaTileEntity>> PandaTile =
            TILE_ENTITIES.register("panda_tile", () -> BlockEntityType.Builder.of(
                    PandaTileEntity::new, DMBlocks.PANDAREBEL.get()).build(null));

    public static RegistryObject<BlockEntityType<ItemShowerTile>> item_shower =
            TILE_ENTITIES.register("item_shower", () -> BlockEntityType.Builder.of(
                    ItemShowerTile::new, DMBlocks.ITEM_SHOWER.get()).build(null));

    public static RegistryObject<BlockEntityType<RedToyotaRotorTileEntity>> red_toyota =
            TILE_ENTITIES.register("red_toyota", () -> BlockEntityType.Builder.of(
                    RedToyotaRotorTileEntity::new, DMBlocks.RedToyota.get()).build(null));

    public static RegistryObject<BlockEntityType<TardisDoorTileEntity>> TARDIS_DOOR =
            TILE_ENTITIES.register("tardis_door_tile", () -> BlockEntityType.Builder.of(
                    TardisDoorTileEntity::new, DMBlocks.TARDIS_DOOR.get()).build(null));

    public static RegistryObject<BlockEntityType<DamagedDalekTileEntity>> DAMAGED_DALEK_TILE_ENTITY =
            TILE_ENTITIES.register("damaged_dalek_tile", () -> BlockEntityType.Builder.of(
                    DamagedDalekTileEntity::new, DMBlocks.DALEK_DAMAGED.get()).build(null));

//    public static RegistryObject<BlockEntityType<ComputerTileEntity>> COMPUTER_TILE_ENTITY =
//            TILE_ENTITIES.register("computer_tile", () -> BlockEntityType.Builder.of(
//                    ComputerTileEntity::new, DMBlocks.COMPUTER.get()).build(null));
//
//    public static RegistryObject<BlockEntityType<MonitorTileEntity>> MONITOR_TILE_ENTITY =
//            TILE_ENTITIES.register("monitor_tile", () -> BlockEntityType.Builder.of(
//                    MonitorTileEntity::new, DMBlocks.MONITOR.get()).build(null));

    public static RegistryObject<BlockEntityType<InfusionTableTileEntity>> INFUSION_TILE_ENTITY =
            TILE_ENTITIES.register("infusion_tile", () -> BlockEntityType.Builder.of(
                    InfusionTableTileEntity::new, DMBlocks.INFUSION_TABLE.get()).build(null));

    public static RegistryObject<BlockEntityType<ChairTileEntity>> CHAIR_TILE_ENTITY =
            TILE_ENTITIES.register("chair_tile", () -> BlockEntityType.Builder.of(
                    ChairTileEntity::new, DMBlocks.INFUSION_TABLE.get()).build(null));

    public static RegistryObject<BlockEntityType<SolarPanelTile>> SOLAR_PANEL =
            TILE_ENTITIES.register("solar_panel", () -> BlockEntityType.Builder.of(
                    SolarPanelTile::new, DMBlocks.SOLAR_PANEL.get()).build(null));

    public static RegistryObject<BlockEntityType<EnergyPipeTileEntity>> ENERGY_PIPE =
            TILE_ENTITIES.register("energy_pipe", () -> BlockEntityType.Builder.of(
                    EnergyPipeTileEntity::new, DMBlocks.SOLAR_PANEL.get()).build(null));



    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
