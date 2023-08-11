package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.common.block.*;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.DMCreativeTabs;
import be.ninedocteur.docmod.common.block.cupdate.*;
import be.ninedocteur.docmod.common.block.energy.EnergyPipeBlock;
import be.ninedocteur.docmod.common.block.energy.SolarPanelBlock;
import be.ninedocteur.docmod.common.block.tardis.TardisLightBlock;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

    public static final RegistryObject<Block> KILLER_BLOCK = createBlock("killer_block", () -> new TestBlockEvent(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(5)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> HEAL_BLOCK = createBlock("heal_block", () -> new HealBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> ZINC_ORE = createBlock("zinc_ore", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = createBlock("deepslate_zinc_ore", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> STEEL_ORE = createBlock("steel_ore", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> DEEPSLATE_STEEL_ORE = createBlock("deepslate_steel_ore", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> ZINC_BLOCK = createBlock("zinc_block", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> STEEL_BLOCK = createBlock("steel_block", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> CRYSTALINE_BLOCK = createBlock("crystaline_block", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(5).sound(SoundType.AMETHYST)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> CRYSTALINE_ORE = createBlock("crystaline_ore", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> CRYSTAL_ORE = createBlock("crystal_ore", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> DEEPSLATE_CRYSTAL_ORE = createBlock("deepslate_crystal_ore", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> XP_ORE = createBlock("xp_ore", () -> new XpOreBlock(Block.Properties.of().requiresCorrectToolForDrops().strength(5f).noOcclusion()), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> CIRCLE_GLASS = createBlock("circle_glass", () -> new CircleGlass(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(5)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> DEATH_LOG = createBlock("death_log", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> CLASSIC_GRASS = createBlock("classic_grass", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(1).sound(SoundType.GRASS)), DMCreativeTabs.TAB.MAIN);
    //public static final RegistryObject<Block> DEATH_SIGN = createBlock("death_sign", () -> new DeathStandingSign(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1), DMWoodTypes.DEATH));
    //public static final RegistryObject<Block> DEATH_WALL_SIGN = createBlock("death_wall_sign", () -> new DeathWallSign(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1), DMWoodTypes.DEATH));
    public static final RegistryObject<Block> DEATH_LEAVES = createBlock("death_leaves", () -> new LeavesBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(0.2f).sound(SoundType.FUNGUS)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> DEATH_GRASS_BLOCK = createBlock("death_grass_block", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> NETHER_ZINC_ORE = createBlock("nether_zinc_ore",() -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> MOON_BLOCK = createBlock("moon_block", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> MOON_STONE = createBlock("moon_stone", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> ALBIZIA_LOG = createBlock("albizia_log", () -> new RotatedPillarBlock(Block.Properties.of().sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> ALBIZIA_PLANKS = createBlock("albizia_planks", () -> new Block(Block.Properties.of().sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> ALBIZIA_STAIRS = createBlock("albizia_stairs", () -> new StairBlock(() -> ALBIZIA_PLANKS.get().defaultBlockState(), Block.Properties.of().sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> ALBIZIA_SLAB = createBlock("albizia_slab", () -> new SlabBlock(Block.Properties.of().sound(SoundType.WOOD).requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> ALBIZIA_LEAVES = createBlock("albizia_leaves", () -> new LeavesBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(5).sound(SoundType.AZALEA_LEAVES).instabreak()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> CRYOLITE_ORE = createBlock("cryolite_ore", () -> new Block(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> HALFINUM_ORE = createBlock("halfinum_ore", () -> new Block(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(8)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> DEEPSLATE_HALFINUM_ORE = createBlock("deepslate_halfinum_ore", () -> new Block(Block.Properties.of().requiresCorrectToolForDrops().strength(3)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> GLASS_TUBE = createBlock("glass_tube", () -> new GlassTubeBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> ZURBTELEPORTER = createBlock("zurb_teleporter", () -> new ZurbTeleporterBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> RED_GLASS_TUBE = createBlock("red_glass_tube", () -> new RedGlassTubeBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> GREEN_GLASS_TUBE = createBlock("green_glass_tube", () -> new GreenGlassTubeBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> ORANGE_GLASS_TUBE = createBlock("orange_glass_tube", () -> new OrangeGlassTubeBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> ELECTRONIC_VINE = createBlock("electronic_vine", () -> new ElectronicVineBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(5).noCollission()), DMCreativeTabs.TAB.MAIN);

    /*
    DECORATION
     */
    public static final RegistryObject<Block> HOUSE_WALL = createBlock("house_wall", () -> new Block(Block.Properties.of().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> HOUSE_WALL_B = createBlock("house_wall_b", () -> new Block(Block.Properties.of().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> FACTORY_BLOCK = createBlock("factory_block", () -> new RotateAbleBlock(Block.Properties.of().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> COUPE = createBlock("coupe", () -> new CoupeBlock(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> MARKS_CONCRETE = createBlock("marks_concrete", () -> new RotateAbleBlock(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> STONE_ROCK = createBlock("stone_rock", () -> new StoneRockRVS(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> BLUE_BRICKS = createBlock("blue_bricks", () -> new RotateAbleBlock(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> GREEN_BRICKS = createBlock("green_bricks", () -> new RotateAbleBlock(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> YELLOW_BRICKS = createBlock("yellow_bricks", () -> new RotateAbleBlock(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> LIGHT_BLUE_BRICKS = createBlock("light_blue_bricks", () -> new RotateAbleBlock(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> GREEN_SCREEN = createBlock("green_screen", () -> new Block(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> CHECKERBOARD_WALLPAPER = createBlock("checkerboard_wallpaper", () -> new Block(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> CREEPER_WALLPAPER = createBlock("creeper_wallpaper", () -> new Block(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> YELLOW_ORANGE_WALLPAPER = createBlock("yellow_orange_wallpaper", () -> new Block(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> SMOKE_BLOCK = createBlock("smoke_block", () -> new SmokeBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(5)), DMCreativeTabs.TAB.MAIN);

    /*
    ROTOR
     */
    // public static final RegistryObject<Block> HartnellRotor = createBlock("hartnell_rotor_block", () -> new HartnellRotorItem(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1)));
    //public static final RegistryObject<Block> HartnellRotor2 = createBlock("hartnell_rotor_block2", () -> new HartnellRotor2Item(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1)));
    public static final RegistryObject<Block> Toyota = createBlock("toyota_rotor", () -> new ToyotaRotorBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> RedToyota = createBlock("toyota_rotor_red", () -> new RedToyotaRotorBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1)), DMCreativeTabs.TAB.MAIN);

    /*
    STATUES
     */
    public static final RegistryObject<Block> NINEDOCTEUR = createBlock("9e_docteur", () -> new StatuesBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1f)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> PANDAREBEL = createBlock("pandarebel4307", () -> new PandaRebelBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1f)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> JOSIA = createBlock("josia50", () -> new StatuesBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1f)), DMCreativeTabs.TAB.MAIN);


    /*
    TOOLS/UTILITY
     */
    public static final RegistryObject<Block> INFUSION_TABLE = createBlock("infusion_table", () -> new InfusionBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1f)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> REDSTONE_LAMP_ON = createBlock("redstone_lamp_on", () -> new DMRedstoneLampOn(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1f)), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> CHAIR = createBlock("chair", () -> new ChairBlock(BlockBehaviour.Properties.of()), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ITEM_SHOWER = createBlock("item_shower", () -> new ItemShowerBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1f).noCollission()), DMCreativeTabs.TAB.MAIN);
    public static final RegistryObject<Block> DALEK_DAMAGED = createBlock("dalek_damaged", () -> new DalekDamagedBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1f)), DMCreativeTabs.TAB.MAIN);
    //public static final RegistryObject<Block> SAFE_CHEST = createBlock("safe_chest", () -> new SafeChestBlock(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1f)));
    /*
    ANNIVERSARY
     */
    public static final RegistryObject<Block> CAKE = createBlock("cake", () -> new AnnivairsaryCakeBlock(BlockBehaviour.Properties.of().noOcclusion()), DMCreativeTabs.TAB.MAIN);


    /*

    /*
    ROUNDEL
     */
    public static final RegistryObject<Block> ROUNDEL_DARK = createBlock("dark_roundel",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_DARK_2 = createBlock("dark_roundel_2",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_YELLOW = createBlock("yellow_roundel",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_YELLOW_2 = createBlock("yellow_roundel_2",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_BLUE = createBlock("blue_roundel",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_BLUE_2 = createBlock("blue_roundel_2",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_RED = createBlock("red_roundel",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_RED_2 = createBlock("red_roundel_2",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);


    public static final RegistryObject<Block> ROUNDEL_GREEN = createBlock("green_roundel",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_GREEN_2 = createBlock("green_roundel_2",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);



    public static final RegistryObject<Block> ROUNDEL_COPPER = createBlock("copper_roundel_2",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_CORAL = createBlock("coral_roundel_2",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);


    public static final RegistryObject<Block> ROUNDEL_RED_CUSTOM= createBlock("red_roundel_custom",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);



    public static final RegistryObject<Block> ROUNDEL_BLUE_CUSTOM = createBlock("blue_roundel_custom",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_LIME_CUSTOM = createBlock("lime_roundel_custom",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);


    //new custom
    public static final RegistryObject<Block> ROUNDEL_GREEN_CUSTOM = createBlock("green_roundel_custom",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_CYAN_CUSTOM = createBlock("cyan_roundel_custom",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_LIGHT_BLUE_CUSTOM = createBlock("light_blue_roundel_custom",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_MAGENTA_CUSTOM = createBlock("magenta_roundel_custom",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_ORANGE_CUSTOM = createBlock("orange_roundel_custom",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_PURPLE_CUSTOM = createBlock("purple_roundel_custom",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_YELLOW_CUSTOM = createBlock("yellow_roundel_custom",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_LAVA = createBlock("lava_roundel",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> ROUNDEL_LAVA_2 = createBlock("lava_roundel_2",
            () -> new Block(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> TOYOTA_LOWER_ROUNDEL = createBlock("toyota_lower_roundel",
            () -> new TardisLightBlock(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.ROUNDELS);

    public static final RegistryObject<Block> TOYOTA_UPPER_ROUNDEL = createBlock("toyota_upper_roundel",
            () -> new TardisLightBlock(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.ROUNDELS);

    public static final RegistryObject<Block> TOYOTA_MID_LIGHT = createBlock("toyota_lower_light",
            () -> new TardisLightBlock(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.ROUNDELS);

    public static final RegistryObject<Block> TOYOTA_UPPER_LIGHT = createBlock("toyota_upper_light",
            () -> new TardisLightBlock(Block.Properties.of().noOcclusion()
                    .requiresCorrectToolForDrops().strength(2)), DMCreativeTabs.TAB.ROUNDELS);




    /*
    -=-=-=-=-=-=-=-=-= DocMod CU Blocks 2021 =-=-=-=-=-=-=-=-=-
     */
    public static final RegistryObject<Block> CHRISTMAS_TREE = createBlock("christmas_tree",
            () -> new ChristmasTree(Block.Properties.of().noOcclusion()), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> SNOW_BALL = createBlock("snow_globe",
            () -> new SnowGlobe(Block.Properties.of().noOcclusion()), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> CHIMNEY = createBlock("chimney",
            () -> new ChimneyBlock(Block.Properties.of().noOcclusion().strength(1).requiresCorrectToolForDrops()), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> RED_CHRISTMAS_BALL = createBlock("red_christmas_ball",
            () -> new RedChristmasBall(Block.Properties.of().noOcclusion()), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> BLUE_CHRISTMAS_BALL = createBlock("blue_christmas_ball",
            () -> new BlueChristmasBall(Block.Properties.of().noOcclusion()), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> GREEN_CHRISTMAS_BALL = createBlock("green_christmas_ball",
            () -> new ChristmasBall(Block.Properties.of().noOcclusion()), DMCreativeTabs.TAB.MAIN);

    public static final RegistryObject<Block> GOLD_CHRISTMAS_BALL = createBlock("gold_christmas_ball",
            () -> new GoldChristmasBall(Block.Properties.of().noOcclusion()), DMCreativeTabs.TAB.MAIN);


    public static final RegistryObject<Block> mars_stone = createBlock("mars_stone", () -> new Block(Block.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(1f)), DMCreativeTabs.TAB.MAIN);
/*
    private static RegistryObject<Block> createBlock(@Nonnull String id, float hardness,
                                                     float resistance, float harvestLevel, SoundType sound, DMCreativeTabs.TAB itemGroup) {
        RegistryObject<Block> block = BLOCKS.register(id, () -> new Block(BlockBehaviour.Properties.of()
                .strength(hardness, resistance).requiresCorrectToolForDrops().destroyTime(harvestLevel).sound(sound)));
        DMItems.createItem(id, () -> new BlockItem(block.get(), new Item.Properties().fireResistant()), itemGroup);
        return block;
    }

 */

    private static <T extends Block> RegistryObject<T> createBlock(@Nonnull String id, Supplier<T> block,
                                                                   DMCreativeTabs.TAB itemGroup) {
        RegistryObject<T> cBlock = BLOCKS.register(id, block);
        DMItems.createItem(id, () -> new BlockItem(cBlock.get(), new Item.Properties().fireResistant()), itemGroup);
        return cBlock;
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
