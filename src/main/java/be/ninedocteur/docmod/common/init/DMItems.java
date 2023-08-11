package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.DMCreativeTabs;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.entity.DMEntityType;
import be.ninedocteur.docmod.common.item.*;
import be.ninedocteur.docmod.common.item.sonic.RobainksSonicItem;
import be.ninedocteur.docmod.common.item.cupdate.twentytwo.ExplosiveBallItem;
import be.ninedocteur.docmod.common.item.cupdate.twentyone.GingerBread;
import be.ninedocteur.docmod.common.item.gun.*;
import be.ninedocteur.docmod.common.item.laser.item.*;
import be.ninedocteur.docmod.common.item.sonic.*;
import be.ninedocteur.docmod.common.item.space.SpaceSuitHelmet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.function.Supplier;

public class DMItems {
    public static final HashMap<String, DMCreativeTabs.TAB> ITEM_TAB_MAP = new HashMap<>();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DocMod.MOD_ID);


    public static final RegistryObject<Item> AMETHYST = createItem("amethyst", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> ZINC_INGOT = createItem("zinc_ingot", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> CRYSTALINE = createItem("crystaline", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> CRYSTAL = createItem("crystal", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)), DMCreativeTabs.TAB.MATERIALS);
    //public static final RegistryObject<Item> RAW_SAPHIR = createItem("raw_saphir", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    //public static final RegistryObject<Item> SAPHIR = createItem("saphir", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> ZINC_NUGGET = createItem("zinc_nugget", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> HALFINUM_NUGGET = createItem("halfinum_nugget", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> CRYOLITE = createItem("cryolite", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> LIGHT_BLUE_BRICKS_INGOT = createItem("light_blue_bricks_ingot", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> GREEN_BRICKS_INGOT = createItem("green_bricks_ingot", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> YELLOW_BRICKS_INGOT = createItem("yellow_bricks_ingot", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> BLUE_BRICKS_INGOT = createItem("blue_bricks_ingot", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> HALFINUM_INGOT = createItem("halfinum_ingot", () -> new HalfinumIngot(new Item.Properties().rarity(Rarity.RARE)), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> FUEL = createItem("fuel", () -> new Item(new Item.Properties().rarity(Rarity.RARE)), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> WHITE_POINTED_STAR = createItem("white_pointed_star", () -> new WhitePointedStar(new Item.Properties().rarity(Rarity.EPIC)), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> STEEL_INGOT = createItem("steel_ingot", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> RAW_STEEL_INGOT = createItem("raw_steel_ingot", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.MATERIALS);
    //public static final RegistryObject<Item> GAZOLINE_BUCKET = createItem("gazoline_bucket", () -> new BucketItem(DMFluids.SOURCE_GAZOLINE, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

    /*
    FOOD
     */
    public static final RegistryObject<Item> BELGIUM_FRIES = createItem("belgium_fries", () -> new FriesFoodItem(new Item.Properties().food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 2), 0.2f).nutrition(2).saturationMod(1).alwaysEat().build())), DMCreativeTabs.TAB.FOOD);
    public static final RegistryObject<Item> TEA = createItem("tea", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).alwaysEat().build())), DMCreativeTabs.TAB.FOOD);
    public static final RegistryObject<Item> COFFEE = createItem("coffee", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 2), 1f).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 200, 3), 1f).nutrition(2).saturationMod(1).alwaysEat().build())), DMCreativeTabs.TAB.FOOD);
    public static final RegistryObject<Item> JUPILER = createItem("jupiler", () -> new JupilerFoodItem(new Item.Properties().food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 150, 2), 0.5f).effect(() -> new MobEffectInstance(MobEffects.POISON, 20, 3), 0.2f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 100, 2), 0.4f).nutrition(2).saturationMod(0.2f).alwaysEat().build())), DMCreativeTabs.TAB.FOOD);
    public static final RegistryObject<Item> GINGERBREAD = createItem("gingerbread",
            () -> new GingerBread(new Item.Properties()
                    .food(new FoodProperties.Builder().effect(() ->
                                    new MobEffectInstance(MobEffects.REGENERATION, 200, 2), 0.5f)
                            .nutrition(2).saturationMod(1.0f).build())), DMCreativeTabs.TAB.FOOD);
    public static final RegistryObject<Item> ICE_CREAM = createItem("ice_cream", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 2), 0.2f).nutrition(4).saturationMod(1).alwaysEat().build())), DMCreativeTabs.TAB.FOOD);
    public static final RegistryObject<Item> HAMBURGER = createItem("hamburger", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 2), 0.2f).nutrition(8).saturationMod(2).alwaysEat().build())), DMCreativeTabs.TAB.FOOD);
    public static final RegistryObject<Item> DOCTEAM_COLA = createItem("docteam_cola", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(2).alwaysEat().build())), DMCreativeTabs.TAB.FOOD);

    /*
    FLASK
     */
    //public static final RegistryObject<Item> EMPTY_FLASK = createItem("empty_flask", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> COPPER_FLASK = createItem("copper_flask", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> ZINC_FLASK = createItem("zinc_flask", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> HALFINUM_FLASK = createItem("halfinum_flask", () -> new Item(new Item.Properties()));

    /*
    TOOLS
     */
    public static final RegistryObject<Item> ZINC_HELMET = createItem("zinc_helmet", () -> new ArmorItem(ModArmorMaterial.ZINC, ArmorItem.Type.HELMET, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> ZINC_CHESTPLATE = createItem("zinc_chestplate", () -> new ArmorItem(ModArmorMaterial.ZINC, ArmorItem.Type.CHESTPLATE, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> ZINC_LEGGINGS = createItem("zinc_leggings", () -> new ArmorItem(ModArmorMaterial.ZINC, ArmorItem.Type.LEGGINGS, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> ZINC_BOOTS = createItem("zinc_boots", () -> new ArmorItem(ModArmorMaterial.ZINC, ArmorItem.Type.BOOTS, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> COPPER_HELMET = createItem("copper_helmet", () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.HELMET, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> COPPER_CHESTPLATE = createItem("copper_chestplate", () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> COPPER_LEGGINGS = createItem("copper_leggings", () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> COPPER_BOOTS = createItem("copper_boots", () -> new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.BOOTS, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> HALFINUM_HELMET = createItem("halfinum_helmet", () -> new ArmorItem(ModArmorMaterial.HALFINUM, ArmorItem.Type.HELMET, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> HALFINUM_CHESTPLATE = createItem("halfinum_chestplate", () -> new ArmorItem(ModArmorMaterial.HALFINUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> HALFINUM_LEGGINGS = createItem("halfinum_leggings", () -> new ArmorItem(ModArmorMaterial.HALFINUM, ArmorItem.Type.LEGGINGS, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> HALFINUM_BOOTS = createItem("halfinum_boots", () -> new ArmorItem(ModArmorMaterial.HALFINUM, ArmorItem.Type.BOOTS, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> SPACE_HELMET = createItem("space_helmet", () -> new SpaceSuitHelmet(ModArmorMaterial.SPACE_SUIT, ArmorItem.Type.HELMET, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> SPACE_CHESTPLATE = createItem("space_chestplate", () -> new ArmorItem(ModArmorMaterial.SPACE_SUIT, ArmorItem.Type.CHESTPLATE, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> SPACE_LEGGINGS = createItem("space_leggings", () -> new ArmorItem(ModArmorMaterial.SPACE_SUIT, ArmorItem.Type.LEGGINGS, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> SPACE_BOOTS = createItem("space_boots", () -> new ArmorItem(ModArmorMaterial.SPACE_SUIT, ArmorItem.Type.BOOTS, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> COPPER_SWORD = createItem("copper_sword", () -> new SwordItem(ModItemTier.COPPER, 6, 3f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> COPPER_PICKAXE = createItem("copper_pickaxe", () -> new PickaxeItem(ModItemTier.COPPER, 0, -1f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> COPPER_AXE = createItem("copper_axe", () -> new AxeItem(ModItemTier.COPPER, 0, -1f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> ZINC_SWORD = createItem("zinc_sword", () -> new SwordItem(ModItemTier.ZINC, 6, 3f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> ZINC_PICKAXE = createItem("zinc_pickaxe", () -> new PickaxeItem(ModItemTier.ZINC, 0, -1f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> ZINC_AXE = createItem("zinc_axe", () -> new AxeItem(ModItemTier.ZINC, 0, -1f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> STAFF = createItem("staff", () -> new StaffItem(ModItemTier.ZINC, 0, -1f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> TARDIS_KEY = createItem("tardis_key", () -> new TardisKeyItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> ALBIZIA_STICK = createItem("albizia_stick", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> HALFINUM_AXE = createItem("halfinum_axe", () -> new AxeItem(ModItemTier.HALFINUM, 0, -1f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> HALFINUM_PICKAXE = createItem("halfinum_pickaxe", () -> new PickaxeItem(ModItemTier.HALFINUM, 0, -1f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> HALFINUM_SHOVEL = createItem("halfinum_shovel", () -> new ShovelItem(ModItemTier.HALFINUM, 0, -1f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> HALFINUM_SWORD = createItem("halfinum_sword", () -> new HalfinumSword(ModItemTier.HALFINUM, 0, -1f, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> MASK = createItem("mask", () -> new ArmorItem(ModArmorMaterial.MASK, ArmorItem.Type.HELMET, new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    //public static final RegistryObject<Item> DEATH_SIGN = createItem("death_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DMBlocks.DEATH_SIGN.get(), DMBlocks.DEATH_WALL_SIGN.get()));
    //public static final RegistryObject<Item> ALBIZIA_SIGN = createItem("albizia_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DMBlocks.ALBIZIA_SIGN.get(), DMBlocks.ALBIZIA_WALL_SIGN.get()));
    public static final RegistryObject<Item> ORE_FINDER = createItem("ore_finder", () -> new OreFinderItem(new Item.Properties().rarity(Rarity.EPIC).durability(100)), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> ELECTRONIC_CICUIT = createItem("electronic_circuit", () -> new Item(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> KNOWCKBACK_STICK = createItem("knockback_stick", () -> new KnockbackStick(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> GENERIC_SONIC_SCREWDRIVER = createItem("generic_sonic", () -> new RobainksSonicItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);

    public static final RegistryObject<Item> SONIC_SCREWDRIVER = createItem("sonic_screwdriver", () -> new RobainksSonicItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> NINEDOCTEUR_SONIC_SCREWDRIVER = createItem("ninedocteur_sonic_screwdriver", () -> new NinedocteurSonic(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> TEN_SONIC_SCREWDRIVER = createItem("sonic_screwdriver_10", () -> new TenthSonic(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> TEN_SONIC_SCREWDRIVER_EXTENDED = createItem("sonic_screwdriver_10_extended", () -> new TenthSonicExtended(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> THEER_SONIC_SCREWDRIVER = createItem("sonic_screwdriver_13", () -> new TheerSonic(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> THEER_SONIC_SCREWDRIVER_OFF = createItem("sonic_screwdriver_13_off", () -> new SonicItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> ROBAINKS_SONIC_SCREWDRIVER= createItem("sonic_screwdriver_robainks", () -> new RobainksSonicItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> GARATIM_SONIC_SCREWDRIVER= createItem("sonic_screwdriver_garatim", () -> new GaratimSonic(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> GARATIM_SONIC_SCREWDRIVER_OFF = createItem("sonic_screwdriver_garatim_off", () -> new SonicItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> POINTER = createItem("pointer", () -> new PointerItem(new Item.Properties().durability(1)), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> SMITH_TOOL = createItem("smith_tool", () -> new SmithItem(new Item.Properties().durability(10)), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> ENDER_LINDER = createItem("ender_linker", () -> new EnderLinker(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);

    //EGG
    public static final RegistryObject<Item> STEVE_EGG = createItem("steve_spawn", () -> new ForgeSpawnEggItem(DMEntityType.OLD_STEVE, 0xffffff, 0xffffff,new Item.Properties()), DMCreativeTabs.TAB.ENTITIES);
    public static final RegistryObject<Item> CYBERMAN_EGG = createItem("cyberman_spawn", () -> new ForgeSpawnEggItem(DMEntityType.CYBERMAN, 0xffffff, 0xffffff,new Item.Properties()), DMCreativeTabs.TAB.ENTITIES);
    public static final RegistryObject<Item> CYBERBOSS_EGG = createItem("cyberboss_spawn", () -> new ForgeSpawnEggItem(DMEntityType.CYBERBOSS, 0xffffff, 0xffffff,new Item.Properties()), DMCreativeTabs.TAB.ENTITIES);
    public static final RegistryObject<Item> CYBERHUMAN_EGG = createItem("cyberhuman_spawn", () -> new ForgeSpawnEggItem(DMEntityType.CYBERHUMAN, 0xffffff, 0xffffff,new Item.Properties()), DMCreativeTabs.TAB.ENTITIES);
    public static final RegistryObject<Item> CYBERHUMAN_2_EGG = createItem("cyberhuman_2_spawn", () -> new ForgeSpawnEggItem(DMEntityType.CYBERHUMAN_2, 0xffffff, 0xffffff,new Item.Properties()), DMCreativeTabs.TAB.ENTITIES);
    public static final RegistryObject<Item> CYBERHUMAN_3_EGG = createItem("cyberhuman_3_spawn", () -> new ForgeSpawnEggItem(DMEntityType.CYBERHUMAN_3, 0xffffff, 0xffffff,new Item.Properties()), DMCreativeTabs.TAB.ENTITIES);
    public static final RegistryObject<Item> DALEK_EGG = createItem("dalek_spawn", () -> new ForgeSpawnEggItem(DMEntityType.DALEK, 0xffffff, 0xffffff,new Item.Properties()), DMCreativeTabs.TAB.ENTITIES);
    public static final RegistryObject<Item> SWDALEK_EGG = createItem("swdalek_spawn", () -> new ForgeSpawnEggItem(DMEntityType.SWDALEK, 0xffffff, 0xffffff,new Item.Properties()), DMCreativeTabs.TAB.ENTITIES);
    //public static final RegistryObject<Item> ADIPOSE_EGG = createItem("adipose_spawn", () -> new ForgeSpawnEggItem(DMEntityType.ADIPOSE, 0xffffff, 0xffffff,new Item.Properties()));

    //LASER
    public static final RegistryObject<Item> CYBER_LASER = createItem("cyber_laser", () -> new LaserItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> DALEK_LASER = createItem("dalek_laser", () -> new DalekLaserItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> RPG_LASER = createItem("rpg_laser", () -> new RPGItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> MAGIC_LASER = createItem("magic_ammo", () -> new MagicLaserItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> CLASSIC_DALEK_LASER = createItem("classic_dalek_laser", () -> new ClassicDalekLaserItem(new Item.Properties()), DMCreativeTabs.TAB.TOOLS);

    //GUN
    public static final RegistryObject<Item> CYBER_GUN = createItem("cyber_gun", () -> new CyberGun(new Item.Properties().durability(2500)), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> DALEK_GUN = createItem("dalek_gun", () -> new DalekGun(new Item.Properties().durability(2500)), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> RPG_GUN = createItem("rpg_gun", () -> new RPGGun(new Item.Properties().durability(2500)), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> MAGIC_WAND = createItem("magic_wand", () -> new MagicWand(new Item.Properties().durability(2500)), DMCreativeTabs.TAB.TOOLS);
    public static final RegistryObject<Item> CLASSIC_DALEK_GUN = createItem("classic_dalek_gun", () -> new ClassicDalekGun(new Item.Properties().durability(2500)), DMCreativeTabs.TAB.TOOLS);

    //COMPUTER
    //public static final RegistryObject<Item> CPU = createItem("cpu", () -> new Item(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    //public static final RegistryObject<Item> GPU = createItem("gpu", () -> new GraphicsUnit(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    // public static final RegistryObject<Item> RAM = createItem("ram", () -> new RAM(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    // public static final RegistryObject<Item> MOTHERBOARD = createItem("motherboard", () -> new Motherboard(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    // public static final RegistryObject<Item> VENTIRAD = createItem("ventirad", () -> new Ventirad(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    //public static final RegistryObject<Item> DISK = createItem("disk", () -> new Disks(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    //public static final RegistryObject<Item> ALIMENTATION = createItem("alimentation", () -> new Alimentation(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    //public static final RegistryObject<Item> HDMI_CABLE = createItem("hdmi_cable", () -> new HDMICable(new Item.Properties().tab(DMCreativeTabs.COMPUTER)));

    public static final RegistryObject<Item> ROUND_THING_FRAME = createItem("round_thing_frame", () -> new Item((new Item.Properties())), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> RED_ROUND_THING_FRAME = createItem("red_round_thing_frame", () -> new Item((new Item.Properties())), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> BLUE_ROUND_THING_FRAME = createItem("blue_round_thing_frame", () -> new Item((new Item.Properties())), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> GREEN_ROUND_THING_FRAME = createItem("green_round_thing_frame", () -> new Item((new Item.Properties())), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> YELLOW_ROUND_THING_FRAME = createItem("yellow_round_thing_frame", () -> new Item((new Item.Properties())), DMCreativeTabs.TAB.MATERIALS);
    public static final RegistryObject<Item> DARK_ROUND_THING_FRAME = createItem("dark_round_thing_frame", () -> new Item((new Item.Properties())), DMCreativeTabs.TAB.MATERIALS);



    // Christmas Update 2022
    public static final RegistryObject<Item> EXPLOSIVE_BALL = createItem("explosive_ball", () -> new ExplosiveBallItem((new Item.Properties())), DMCreativeTabs.TAB.TOOLS);

    public static <T extends Item> RegistryObject<T> createItem(String id, Supplier<T> item, DMCreativeTabs.TAB tab){
        ITEM_TAB_MAP.put(id, tab);
        return ITEMS.register(id, item);
    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
