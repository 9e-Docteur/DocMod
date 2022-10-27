package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.common.block.*;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.DMCreativeTabs;
import be.ninedocteur.docmod.common.block.cupdate.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class DMBlocks {
    /*
    SOMES BLOCK ARE TEMPORAIRLY DISABLE DUE TO WAITING UTILS OR OTHER REASON. THEY WILL BE ACTIVATED LATER
     */

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DocMod.MOD_ID);

    public static final RegistryObject<Block> KILLER_BLOCK = registerBlock("killer_block", () -> new TestBlockEvent(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(5)));
    public static final RegistryObject<Block> HEAL_BLOCK = registerBlock("heal_block", () -> new HealBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(3)));
    public static final RegistryObject<Block> ZINC_ORE = DMOreBlock("zinc_ore", Material.STONE, MaterialColor.COLOR_BLUE, 4, 10, 4, SoundType.STONE, DMCreativeTabs.BETA);
    public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = DMOreBlock("deepslate_zinc_ore", Material.STONE, MaterialColor.COLOR_BLACK, 6, 10, 4, SoundType.STONE, DMCreativeTabs.BETA);
    public static final RegistryObject<Block> STEEL_ORE = DMOreBlock("steel_ore", Material.STONE, MaterialColor.COLOR_BLACK, 4, 10, 4, SoundType.STONE, DMCreativeTabs.BETA);
    public static final RegistryObject<Block> DEEPSLATE_STEEL_ORE = DMOreBlock("deepslate_steel_ore", Material.STONE, MaterialColor.COLOR_BLACK, 6, 10, 4, SoundType.STONE, DMCreativeTabs.BETA);
    public static final RegistryObject<Block> ZINC_BLOCK = registerBlock("zinc_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new Block(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3)));
    public static final RegistryObject<Block> CRYSTALINE_BLOCK = registerBlock("crystaline_block", () -> new Block(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> CRYSTALINE_ORE = DMOreBlock("crystaline_ore", Material.STONE, MaterialColor.COLOR_RED, 4, 10, 4, SoundType.STONE, DMCreativeTabs.BETA);
    public static final RegistryObject<Block> CRYSTAL_ORE = DMOreBlock("crystal_ore", Material.STONE, MaterialColor.COLOR_BLUE, 4, 10, 4, SoundType.STONE, DMCreativeTabs.BETA);
    public static final RegistryObject<Block> DEEPSLATE_CRYSTAL_ORE = DMOreBlock("deepslate_crystal_ore", Material.STONE, MaterialColor.COLOR_BLACK, 6, 10, 4, SoundType.STONE, DMCreativeTabs.BETA);
    public static final RegistryObject<Block> XP_ORE = registerBlock("xp_ore", () -> new XpOreBlock(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5f).noOcclusion()));

    public static final RegistryObject<Block> CIRCLE_GLASS = registerBlock("circle_glass", () -> new CircleGlass(Block.Properties.of(Material.GLASS).noOcclusion().requiresCorrectToolForDrops().strength(5)));
    public static final RegistryObject<Block> DEATH_LOG = registerBlock("death_log", () -> new Block(Block.Properties.of(Material.WOOD).requiresCorrectToolForDrops().strength(3)));
    //public static final RegistryObject<Block> DEATH_PLANKS = registerBlock("death_planks", () -> new Block(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
   // public static final RegistryObject<Block> DEATH_STAIRS = registerBlock("death_stairs", () -> new StairBlock(() -> DEATH_PLANKS.get().defaultBlockState(), Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
   // public static final RegistryObject<Block> DEATH_SLAB = registerBlock("death_slab", () -> new SlabBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
   // public static final RegistryObject<Block> DEATH_FENCE = registerBlock("death_fence", () -> new FenceBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
   // public static final RegistryObject<Block> DEATH_FENCE_GATE = registerBlock("death_fence_gate", () -> new FenceGateBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
    //public static final RegistryObject<Block> DEATH_DOOR = registerBlock("death_door", () -> new DoorBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
    //public static final RegistryObject<Block> DEATH_TRAPDOOR = registerBlock("death_trapdoor", () -> new TrapDoorBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
   /* public static final RegistryObject<Block> DEATH_BUTTON = registerBlock("death_button", () -> new ButtonBlock(true, Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)) {
        @Override
        protected SoundEvent getSound(boolean pIsOn) {
            return SoundEvents.WOODEN_BUTTON_CLICK_ON;
        }
    });

    */
    public static final RegistryObject<Block> CLASSIC_GRASS = registerBlock("classic_grass", () -> new Block(Block.Properties.of(Material.GRASS).requiresCorrectToolForDrops().strength(1).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> DEATH_SIGN = registerBlockOnly("death_sign", () -> new DeathStandingSign(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1), DMWoodTypes.DEATH));
    public static final RegistryObject<Block> DEATH_WALL_SIGN = registerBlockOnly("death_wall_sign", () -> new DeathWallSign(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1), DMWoodTypes.DEATH));
    public static final RegistryObject<Block> DEATH_LEAVES = registerBlock("death_leaves", () -> new LeavesBlock(Block.Properties.of(Material.LEAVES).noOcclusion().requiresCorrectToolForDrops().strength(0.2f).sound(SoundType.FUNGUS)));
    public static final RegistryObject<Block> DEATH_GRASS_BLOCK = registerBlock("death_grass_block", () -> new Block(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3)));
    public static final RegistryObject<Block> NETHER_ZINC_ORE = DMOreBlock("nether_zinc_ore", Material.STONE, MaterialColor.COLOR_RED, 2, 2, 2, SoundType.NETHER_ORE, DMCreativeTabs.BETA);
    public static final RegistryObject<Block> MOON_BLOCK = registerBlock("moon_block", () -> new Block(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> MOON_STONE = registerBlock("moon_stone", () -> new Block(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> ALBIZIA_LOG = registerBlock("albizia_log", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> ALBIZIA_PLANKS = registerBlock("albizia_planks", () -> new Block(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> ALBIZIA_STAIRS = registerBlock("albizia_stairs", () -> new StairBlock(() -> ALBIZIA_PLANKS.get().defaultBlockState(), Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> ALBIZIA_SLAB = registerBlock("albizia_slab", () -> new SlabBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
    //public static final RegistryObject<Block> ALBIZIA_FENCE = registerBlock("albizia_fence", () -> new FenceBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
    //public static final RegistryObject<Block> ALBIZIA_FENCE_GATE = registerBlock("albizia_fence_gate", () -> new FenceGateBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> ALBIZIA_DOOR = registerBlock("albizia_door", () -> new DoorBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
    //public static final RegistryObject<Block> ALBIZIA_TRAPDOOR = registerBlock("albizia_trapdoor", () -> new TrapDoorBlock(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
   // public static final RegistryObject<Block> ALBIZIA_BUTTON = registerBlock("albizia_button", () -> new ButtonBlock(true, Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)) {
     //  @Override
      // protected SoundEvent getSound(boolean pIsOn) {
       //    return SoundEvents.WOODEN_BUTTON_CLICK_ON;
       // }
   // });
   // public static final RegistryObject<Block> ALBIZIA_PRESSURE_PLATE = registerBlock("albizia_presure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> ALBIZIA_SIGN = registerBlockOnly("albizia_sign", () -> new AlbiziaStandingSign(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1), DMWoodTypes.ALBIZIA));
    public static final RegistryObject<Block> ALBIZIA_WALL_SIGN = registerBlockOnly("albizia_wall_sign", () -> new AlbiziaWallSign(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1), DMWoodTypes.ALBIZIA));
    public static final RegistryObject<Block> ALBIZIA_LEAVES = registerBlock("albizia_leaves", () -> new LeavesBlock(Block.Properties.of(Material.LEAVES).noOcclusion().requiresCorrectToolForDrops().strength(5).sound(SoundType.AZALEA_LEAVES)));
    public static final RegistryObject<Block> CRYOLITE_ORE = registerBlock("cryolite_ore", () -> new Block(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(3)));
    public static final RegistryObject<Block> HALFINUM_ORE = registerBlock("halfinum_ore", () -> new Block(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(8)));
    public static final RegistryObject<Block> DEEPSLATE_HALFINUM_ORE = DMOreBlock("deepslate_halfinum_ore", Material.STONE, MaterialColor.COLOR_BLACK, 6, 10, 4, SoundType.STONE, DMCreativeTabs.BETA);
    public static final RegistryObject<Block> Tardis = registerHidenBlock("tardis", () -> new TardisBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> GLASS_TUBE = registerBlock("glass_tube", () -> new GlassTubeBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> ZURBTELEPORTER = registerBlock("zurb_teleporter", () -> new ZurbTeleporterBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> RED_GLASS_TUBE = registerBlock("red_glass_tube", () -> new RedGlassTubeBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> GREEN_GLASS_TUBE = registerBlock("green_glass_tube", () -> new GreenGlassTubeBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> ORANGE_GLASS_TUBE = registerBlock("orange_glass_tube", () -> new OrangeGlassTubeBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> ELECTRONIC_VINE = registerBlock("electronic_vine", () -> new ElectronicVineBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(5).noCollission()));

    /*
    DECORATION
     */
    public static final RegistryObject<Block> HOUSE_WALL = registerBlock("house_wall", () -> new Block(Block.Properties.of(Material.STONE).strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HOUSE_WALL_B = registerBlock("house_wall_b", () -> new Block(Block.Properties.of(Material.STONE).strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FACTORY_BLOCK = registerBlock("factory_block", () -> new RotateAbleBlock(Block.Properties.of(Material.STONE).strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> COUPE = registerBlock("coupe", () -> new CoupeBlock(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MARKS_CONCRETE = registerBlock("marks_concrete", () -> new RotateAbleBlock(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STONE_ROCK = registerBlock("stone_rock", () -> new StoneRockRVS(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUE_BRICKS = registerBlock("blue_bricks", () -> new RotateAbleBlock(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_BRICKS = registerBlock("green_bricks", () -> new RotateAbleBlock(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_BRICKS = registerBlock("yellow_bricks", () -> new RotateAbleBlock(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIGHT_BLUE_BRICKS = registerBlock("light_blue_bricks", () -> new RotateAbleBlock(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_SCREEN = registerBlock("green_screen", () -> new Block(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHECKERBOARD_WALLPAPER = registerBlock("checkerboard_wallpaper", () -> new Block(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CREEPER_WALLPAPER = registerBlock("creeper_wallpaper", () -> new Block(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_ORANGE_WALLPAPER = registerBlock("yellow_orange_wallpaper", () -> new Block(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMOKE_BLOCK = registerBlock("smoke_block", () -> new SmokeBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(5)));
    //public static final RegistryObject<Block> IMAGE = registerBlock("image", () -> new Block(Block.Properties.of(Material.STONE).noOcclusion().strength(1).requiresCorrectToolForDrops()));

    /*
    ROTOR
     */
   // public static final RegistryObject<Block> HartnellRotor = registerBlock("hartnell_rotor_block", () -> new HartnellRotorItem(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1)));
    //public static final RegistryObject<Block> HartnellRotor2 = registerBlock("hartnell_rotor_block2", () -> new HartnellRotor2Item(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> Toyota = registerBlock("toyota_rotor", () -> new ToyotaRotorBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> RedToyota = registerBlock("toyota_rotor_red", () -> new RedToyotaRotorBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1)));

    /*
    STATUES
     */
    public static final RegistryObject<Block> NINEDOCTEUR = registerBlock("9e_docteur", () -> new StatuesBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));

    public static final RegistryObject<Block> PANDAREBEL = registerBlock("pandarebel4307", () -> new PandaRebelBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));

    public static final RegistryObject<Block> JOSIA = registerBlock("josia50", () -> new StatuesBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));

    /*
    CHAIR
     */
   // public static final RegistryObject<Block> CHAIR = registerBlock("chair", () -> new Block(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));

    /*
    TOOLS/UTILITY
     */
    public static final RegistryObject<Block> LIGHT_BLOCK = registerBlock("light_block", () -> new Block(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f).noCollission().lightLevel((p_152686_) -> {
        return 15;
    })));
    public static final RegistryObject<Block> INFUSION_TABLE = registerBlock("infusion_table", () -> new InfusionBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));

    public static final RegistryObject<Block> REDSTONE_LAMP_ON = registerHidenBlock("redstone_lamp_on", () -> new DMRedstoneLampOn(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f).lightLevel((p_152686_) -> {
        return 15;
    })));
    //public static final RegistryObject<LiquidBlock> GAZOLINE_BLOCK = registerBlock("gazoline_block", () -> new LiquidBlock(DMFluids.SOURCE_GAZOLINE, BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<Block> CHAIR = registerBlock("chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.STONE)));

   // public static final RegistryObject<Block> PIG_FARMER = registerHidenBlock("pig_farmer", () -> new PigFarmerBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));
    public static final RegistryObject<Block> ITEM_SHOWER = registerHidenBlock("item_shower", () -> new ItemShowerBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f).noCollission()));
    public static final RegistryObject<Block> EXPLOSIVE_DEVICE = registerHidenBlock("explosive_device", () -> new ExplosiveDevice(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));
    public static final RegistryObject<Block> HANDBRAKE = registerHidenBlock("handbrake", () -> new HandBrakeBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));
    public static final RegistryObject<Block> TARDIS_DOOR = registerHidenBlock("tardis_door", () -> new TardisDoor(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));
    public static final RegistryObject<Block> DALEK_DAMAGED = registerHidenBlock("dalek_damaged", () -> new DalekDamagedBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));
    public static final RegistryObject<Block> ZINC_CRAFTING_TABLE = registerBlock("zinc_crafting_table", () -> new ZincCraftingTable(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));

    /*
    ANNIVERSARY
     */
    public static final RegistryObject<Block> CAKE = registerAnnivairsaryBlock("cake", () -> new AnnivairsaryCakeBlock(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));

    /*
    COMPUTER
     */
    //public static final RegistryObject<Block> COMPUTER = registerComputerBlock("computer", () -> new ComputerBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));
    //public static final RegistryObject<Block> MONITOR = registerComputerBlock("monitor", () -> new MonitorBlock(Block.Properties.of(Material.STONE).noOcclusion().requiresCorrectToolForDrops().strength(1f)));

    /*
    ROUNDEL
     */
    public static final RegistryObject<Block> ROUNDEL_DARK = registerRoundelBlock("dark_roundel",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_DARK_2 = registerRoundelBlock("dark_roundel_2",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_YELLOW = registerRoundelBlock("yellow_roundel",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_YELLOW_2 = registerRoundelBlock("yellow_roundel_2",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_BLUE = registerRoundelBlock("blue_roundel",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_BLUE_2 = registerRoundelBlock("blue_roundel_2",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_RED = registerRoundelBlock("red_roundel",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_RED_2 = registerRoundelBlock("red_roundel_2",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));


    public static final RegistryObject<Block> ROUNDEL_GREEN = registerRoundelBlock("green_roundel",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_GREEN_2 = registerRoundelBlock("green_roundel_2",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));



    public static final RegistryObject<Block> ROUNDEL_COPPER = registerRoundelBlock("copper_roundel_2",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_CORAL = registerRoundelBlock("coral_roundel_2",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));


    public static final RegistryObject<Block> ROUNDEL_RED_CUSTOM= registerRoundelBlock("red_roundel_custom",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));



    public static final RegistryObject<Block> ROUNDEL_BLUE_CUSTOM = registerRoundelBlock("blue_roundel_custom",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_LIME_CUSTOM = registerRoundelBlock("lime_roundel_custom",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));


    //new custom
    public static final RegistryObject<Block> ROUNDEL_GREEN_CUSTOM = registerRoundelBlock("green_roundel_custom",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_CYAN_CUSTOM = registerRoundelBlock("cyan_roundel_custom",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_LIGHT_BLUE_CUSTOM = registerRoundelBlock("light_blue_roundel_custom",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_MAGENTA_CUSTOM = registerRoundelBlock("magenta_roundel_custom",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_ORANGE_CUSTOM = registerRoundelBlock("orange_roundel_custom",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_PURPLE_CUSTOM = registerRoundelBlock("purple_roundel_custom",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_YELLOW_CUSTOM = registerRoundelBlock("yellow_roundel_custom",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_LAVA = registerRoundelBlock("lava_roundel",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));

    public static final RegistryObject<Block> ROUNDEL_LAVA_2 = registerRoundelBlock("lava_roundel_2",
            () -> new Block(Block.Properties.of(Material.STONE).noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)));
    
    


    /*
    -=-=-=-=-=-=-=-=-= DocMod CU Blocks =-=-=-=-=-=-=-=-=-
     */
    public static final RegistryObject<Block> CHRISTMAS_TREE = registerCUBlock("christmas_tree",
            () -> new ChristmasTree(Block.Properties.of(Material.STONE).noOcclusion()));

    public static final RegistryObject<Block> SNOW_BALL = registerCUBlock("snow_globe",
            () -> new SnowGlobe(Block.Properties.of(Material.STONE).noOcclusion()));

    //public static final RegistryObject<Block> CHIMNEY = registerBlock("chimney",
    //() -> new ChimneyBlock(Block.Properties.of(Material.STONE).noOcclusion()
    //  .strength(1).requiresCorrectToolForDrops().hardnessAndResistance(1f)));

    public static final RegistryObject<Block> RED_CHRISTMAS_BALL = registerCUBlock("red_christmas_ball",
            () -> new RedChristmasBall(Block.Properties.of(Material.STONE).noOcclusion()));

    public static final RegistryObject<Block> BLUE_CHRISTMAS_BALL = registerCUBlock("blue_christmas_ball",
            () -> new BlueChristmasBall(Block.Properties.of(Material.STONE).noOcclusion()));

    public static final RegistryObject<Block> GREEN_CHRISTMAS_BALL = registerCUBlock("green_christmas_ball",
            () -> new ChristmasBall(Block.Properties.of(Material.STONE).noOcclusion()));

    public static final RegistryObject<Block> GOLD_CHRISTMAS_BALL = registerCUBlock("gold_christmas_ball",
            () -> new GoldChristmasBall(Block.Properties.of(Material.STONE).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerAnnivairsaryBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerAnnivairsaryBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerHidenBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerHidenBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerCUBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerCUBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerRoundelBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerRoundelBlockItem(name, toReturn);
        return toReturn;
    }

//    private static <T extends Block> RegistryObject<T> registerComputerBlock(String name, Supplier<T> block) {
//        RegistryObject<T> toReturn = BLOCKS.register(name, block);
//        registerComputerBlockItem(name, toReturn);
//        return toReturn;
//    }

    private static <T extends Block> RegistryObject<T> registerBlockOnly(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block> void registerAnnivairsaryBlockItem(String name, RegistryObject<T> block) {
        DMItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(DMCreativeTabs.ANNIVERSARY)));
    }


        private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        DMItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(DMCreativeTabs.BETA)));
    }

    private static <T extends Block> void registerHidenBlockItem(String name, RegistryObject<T> block) {
        DMItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    private static <T extends Block> void registerCUBlockItem(String name, RegistryObject<T> block) {
        DMItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(DMCreativeTabs.CHRISTMAS)));
    }

    private static <T extends Block> void registerRoundelBlockItem(String name, RegistryObject<T> block) {
        DMItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(DMCreativeTabs.ROUNDEL)));
    }

//    private static <T extends Block> void registerComputerBlockItem(String name, RegistryObject<T> block) {
//        DMItems.ITEMS.register(name, () -> new BlockItem(block.get(),
//                new Item.Properties().tab(DMCreativeTabs.COMPUTER)));
//    }

    private static RegistryObject<Block> DMOreBlock(@Nonnull String id, Material material, MaterialColor color, float hardness, float resistance, float strength, SoundType sound, CreativeModeTab itemGroup){
        RegistryObject<Block> block = BLOCKS.register(id, () -> new DropExperienceBlock(BlockBehaviour.Properties.of(material, color).strength(hardness, resistance).requiresCorrectToolForDrops().destroyTime(strength).sound(sound)));
        DMItems.ITEMS.register(id, () -> new BlockItem(block.get(), new Item.Properties().tab(DMCreativeTabs.BETA).fireResistant()));
        return block;
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
