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

//   public static RegistryObject<BlockEntityType<DeathSignTile>> DeathSign =
//            TILE_ENTITIES.register("death_sign", () -> BlockEntityType.Builder.of(
//                    DeathSignTile::new, DMBlocks.DEATH_SIGN.get(), DMBlocks.DEATH_WALL_SIGN.get()).build(null));

//   public static RegistryObject<BlockEntityType<AlbiziaSignTile>> AlbiziaSign =
//            TILE_ENTITIES.register("albizia_sign", () -> BlockEntityType.Builder.of(
//                    AlbiziaSignTile::new, DMBlocks.ALBIZIA_SIGN.get(), DMBlocks.ALBIZIA_WALL_SIGN.get()).build(null));
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

    public static RegistryObject<BlockEntityType<EnergyPipeTileEntity>> DAMAGED_DALEK =
            TILE_ENTITIES.register("damage_dalek", () -> BlockEntityType.Builder.of(
                    EnergyPipeTileEntity::new, DMBlocks.DALEK_DAMAGED.get()).build(null));

//    public static RegistryObject<BlockEntityType<SafeChestTileEntity>> SAFECHEST =
//            TILE_ENTITIES.register("safe_chest", () -> BlockEntityType.Builder.of(
//                    SafeChestTileEntity::new, DMBlocks.SAFE_CHEST.get()).build(null));

    public static RegistryObject<BlockEntityType<HologramTileEntity>> HOLOGRAM =
            TILE_ENTITIES.register("hologram", () -> BlockEntityType.Builder.of(
                    HologramTileEntity::new, DMBlocks.HOLOGRAM.get()).build(null));

    public static RegistryObject<BlockEntityType<HartnellTileEntity>> HARTNELL =
            TILE_ENTITIES.register("hartnell_rotor_tile", () -> BlockEntityType.Builder.of(
            		HartnellTileEntity::new, DMBlocks.HARTNELL.get()).build(null));
    
    public static RegistryObject<BlockEntityType<ImageTileEntity>> IMAGE =
            TILE_ENTITIES.register("image_tile", () -> BlockEntityType.Builder.of(
            		ImageTileEntity::new, DMBlocks.IMAGE_LOADER.get()).build(null));
    
    public static RegistryObject<BlockEntityType<EnderPadTile>> ENDER_TILE =
            TILE_ENTITIES.register("enderpad_tile", () -> BlockEntityType.Builder.of(
            		EnderPadTile::new, DMBlocks.ENDER_PAD.get()).build(null));
    
    public static RegistryObject<BlockEntityType<LightSensorTile>> LIGHT_SENSOR =
            TILE_ENTITIES.register("light_sensor", () -> BlockEntityType.Builder.of(
            		LightSensorTile::new, DMBlocks.LIGHT_SENSOR.get()).build(null));
    
    public static RegistryObject<BlockEntityType<GrassTile>> GRASS_TILE =
            TILE_ENTITIES.register("grass_tile", () -> BlockEntityType.Builder.of(
            		GrassTile::new, DMBlocks.GRASS.get()).build(null));
    
    public static RegistryObject<BlockEntityType<GutterTileEntity>> GUTTER_TILE =
            TILE_ENTITIES.register("gutter_tile", () -> BlockEntityType.Builder.of(
            		GutterTileEntity::new, DMBlocks.GUTTER_BLOCK.get()).build(null));


    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
