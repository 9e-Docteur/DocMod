package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.DMCreativeTabs;
import be.ninedocteur.docmod.common.entity.DMEntityType;
import be.ninedocteur.docmod.common.item.*;
import be.ninedocteur.docmod.common.item.computer.HDMICable;
import be.ninedocteur.docmod.common.item.computer.parts.*;
import be.ninedocteur.docmod.common.item.gun.*;
import be.ninedocteur.docmod.common.item.laser.item.*;
import be.ninedocteur.docmod.common.item.sonic.*;
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

public class DMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DocMod.MOD_ID);


    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> CRYSTALINE = ITEMS.register("crystaline", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> CRYSTAL = ITEMS.register("crystal", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS).rarity(Rarity.UNCOMMON)));
    //public static final RegistryObject<Item> RAW_SAPHIR = ITEMS.register("raw_saphir", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS).rarity(Rarity.EPIC)));
    //public static final RegistryObject<Item> SAPHIR = ITEMS.register("saphir", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> ZINC_NUGGET = ITEMS.register("zinc_nugget", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> HALFINUM_NUGGET = ITEMS.register("halfinum_nugget", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> CRYOLITE = ITEMS.register("cryolite", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> LIGHT_BLUE_BRICKS_INGOT = ITEMS.register("light_blue_bricks_ingot", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> GREEN_BRICKS_INGOT = ITEMS.register("green_bricks_ingot", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> YELLOW_BRICKS_INGOT = ITEMS.register("yellow_bricks_ingot", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> BLUE_BRICKS_INGOT = ITEMS.register("blue_bricks_ingot", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> HALFINUM_INGOT = ITEMS.register("halfinum_ingot", () -> new HalfinumIngot(new Item.Properties().tab(DMCreativeTabs.MATERIALS).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> FUEL = ITEMS.register("fuel", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> WHITE_POINTED_STAR = ITEMS.register("white_pointed_star", () -> new WhitePointedStar(new Item.Properties().tab(DMCreativeTabs.MATERIALS).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> RAW_STEEL_INGOT = ITEMS.register("raw_steel_ingot", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    //public static final RegistryObject<Item> GAZOLINE_BUCKET = ITEMS.register("gazoline_bucket", () -> new BucketItem(DMFluids.SOURCE_GAZOLINE, new Item.Properties().tab(DMCreativeTabs.MATERIALS).stacksTo(1).craftRemainder(Items.BUCKET)));

    /*
    FOOD
     */
    public static final RegistryObject<Item> BELGIUM_FRIES = ITEMS.register("belgium_fries", () -> new FriesFoodItem(new Item.Properties().tab(DMCreativeTabs.FOOD).food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 2), 0.2f).nutrition(2).saturationMod(1).alwaysEat().build())));
    public static final RegistryObject<Item> TEA = ITEMS.register("tea", () -> new Item(new Item.Properties().tab(DMCreativeTabs.FOOD).food(new FoodProperties.Builder().nutrition(2).saturationMod(1).alwaysEat().build())));
    public static final RegistryObject<Item> COFFEE = ITEMS.register("coffee", () -> new Item(new Item.Properties().tab(DMCreativeTabs.FOOD).food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 2), 1f).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 200, 3), 1f).nutrition(2).saturationMod(1).alwaysEat().build())));
    public static final RegistryObject<Item> JUPILER = ITEMS.register("jupiler", () -> new JupilerFoodItem(new Item.Properties().tab(DMCreativeTabs.FOOD).food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 150, 2), 0.5f).effect(() -> new MobEffectInstance(MobEffects.POISON, 20, 3), 0.2f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 100, 2), 0.4f).nutrition(2).saturationMod(0.2f).alwaysEat().build())));
    public static final RegistryObject<Item> GINGERBREAD = ITEMS.register("gingerbread",
            () -> new Item(new Item.Properties().tab(DMCreativeTabs.CHRISTMAS)
                    .food(new FoodProperties.Builder().effect(() ->
                                    new MobEffectInstance(MobEffects.REGENERATION, 200, 2), 0.5f)
                            .nutrition(2).saturationMod(1.0f).build())));
    public static final RegistryObject<Item> ICE_CREAM = ITEMS.register("ice_cream", () -> new Item(new Item.Properties().tab(DMCreativeTabs.FOOD).food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 2), 0.2f).nutrition(4).saturationMod(1).alwaysEat().build())));
    public static final RegistryObject<Item> HAMBURGER = ITEMS.register("hamburger", () -> new Item(new Item.Properties().tab(DMCreativeTabs.FOOD).food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 2), 0.2f).nutrition(8).saturationMod(2).alwaysEat().build())));
    public static final RegistryObject<Item> DOCTEAM_COLA = ITEMS.register("docteam_cola", () -> new Item(new Item.Properties().tab(DMCreativeTabs.FOOD).food(new FoodProperties.Builder().nutrition(5).saturationMod(2).alwaysEat().build())));

    /*
    FLASK
     */
    public static final RegistryObject<Item> EMPTY_FLASK = ITEMS.register("empty_flask", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_FLASK = ITEMS.register("copper_flask", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZINC_FLASK = ITEMS.register("zinc_flask", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HALFINUM_FLASK = ITEMS.register("halfinum_flask", () -> new Item(new Item.Properties()));

    /*
    TOOLS
     */
    public static final RegistryObject<Item> ZINC_HELMET = ITEMS.register("zinc_helmet", () -> new ArmorItem(ModArmorMaterial.ZINC, EquipmentSlot.HEAD, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> ZINC_CHESTPLATE = ITEMS.register("zinc_chestplate", () -> new ArmorItem(ModArmorMaterial.ZINC, EquipmentSlot.CHEST, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> ZINC_LEGGINGS = ITEMS.register("zinc_leggings", () -> new ArmorItem(ModArmorMaterial.ZINC, EquipmentSlot.LEGS, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> ZINC_BOOTS = ITEMS.register("zinc_boots", () -> new ArmorItem(ModArmorMaterial.ZINC, EquipmentSlot.FEET, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet", () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.HEAD, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.CHEST, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings", () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.LEGS, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots", () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.FEET, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> HALFINUM_HELMET = ITEMS.register("halfinum_helmet", () -> new ArmorItem(ModArmorMaterial.HALFINUM, EquipmentSlot.HEAD, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> HALFINUM_CHESTPLATE = ITEMS.register("halfinum_chestplate", () -> new ArmorItem(ModArmorMaterial.HALFINUM, EquipmentSlot.CHEST, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> HALFINUM_LEGGINGS = ITEMS.register("halfinum_leggings", () -> new ArmorItem(ModArmorMaterial.HALFINUM, EquipmentSlot.LEGS, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> HALFINUM_BOOTS = ITEMS.register("halfinum_boots", () -> new ArmorItem(ModArmorMaterial.HALFINUM, EquipmentSlot.FEET, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", () -> new SwordItem(ModItemTier.COPPER, 6, 3f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new PickaxeItem(ModItemTier.COPPER, 0, -1f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", () -> new AxeItem(ModItemTier.COPPER, 0, -1f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> ZINC_SWORD = ITEMS.register("zinc_sword", () -> new SwordItem(ModItemTier.ZINC, 6, 3f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> ZINC_PICKAXE = ITEMS.register("zinc_pickaxe", () -> new PickaxeItem(ModItemTier.ZINC, 0, -1f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> ZINC_AXE = ITEMS.register("zinc_axe", () -> new AxeItem(ModItemTier.ZINC, 0, -1f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> STAFF = ITEMS.register("staff", () -> new StaffItem(ModItemTier.ZINC, 0, -1f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> ALBIZIA_STICK = ITEMS.register("albizia_stick", () -> new Item(new Item.Properties().tab(DMCreativeTabs.MATERIALS)));
    public static final RegistryObject<Item> HALFINUM_AXE = ITEMS.register("halfinum_axe", () -> new AxeItem(ModItemTier.HALFINUM, 0, -1f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> HALFINUM_PICKAXE = ITEMS.register("halfinum_pickaxe", () -> new PickaxeItem(ModItemTier.HALFINUM, 0, -1f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> HALFINUM_SHOVEL = ITEMS.register("halfinum_shovel", () -> new ShovelItem(ModItemTier.HALFINUM, 0, -1f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> HALFINUM_SWORD = ITEMS.register("halfinum_sword", () -> new HalfinumSword(ModItemTier.HALFINUM, 0, -1f, new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> MASK = ITEMS.register("mask", () -> new ArmorItem(ModArmorMaterial.MASK, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> DEATH_SIGN = ITEMS.register("death_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(DMCreativeTabs.BETA), DMBlocks.DEATH_SIGN.get(), DMBlocks.DEATH_WALL_SIGN.get()));
    public static final RegistryObject<Item> ALBIZIA_SIGN = ITEMS.register("albizia_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(DMCreativeTabs.BETA), DMBlocks.ALBIZIA_SIGN.get(), DMBlocks.ALBIZIA_WALL_SIGN.get()));
    public static final RegistryObject<Item> ORE_FINDER = ITEMS.register("ore_finder", () -> new OreFinderItem(new Item.Properties().tab(DMCreativeTabs.TOOLS).rarity(Rarity.EPIC).durability(100)));
    public static final RegistryObject<Item> ELECTRONIC_CICUIT = ITEMS.register("electronic_circuit", () -> new Item(new Item.Properties().tab(DMCreativeTabs.TOOLS)));
//    public static final RegistryObject<Item> KNOWCKBACK_STICK = ITEMS.register("knockback_stick", () -> new KnockbackStick(new Item.Properties()));
    public static final RegistryObject<Item> SONIC_SCREWDRIVER = ITEMS.register("sonic_screwdriver", () -> new SonicItem(new Item.Properties()));
    public static final RegistryObject<Item> TEN_SONIC_SCREWDRIVER = ITEMS.register("sonic_screwdriver_10", () -> new TenthSonic(new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> TEN_SONIC_SCREWDRIVER_EXTENDED = ITEMS.register("sonic_screwdriver_10_extended", () -> new TenthSonicExtended(new Item.Properties()));
    public static final RegistryObject<Item> THEER_SONIC_SCREWDRIVER = ITEMS.register("sonic_screwdriver_13", () -> new TheerSonic(new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> THEER_SONIC_SCREWDRIVER_OFF = ITEMS.register("sonic_screwdriver_13_off", () -> new TheerSonicOff(new Item.Properties()));
    public static final RegistryObject<Item> ROBAINKS_SONIC_SCREWDRIVER= ITEMS.register("sonic_screwdriver_robainks", () -> new SonicItem(new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> GARATIM_SONIC_SCREWDRIVER= ITEMS.register("sonic_screwdriver_garatim", () -> new GaratimSonic(new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> GARATIM_SONIC_SCREWDRIVER_OFF = ITEMS.register("sonic_screwdriver_garatim_off", () -> new GaratimSonicOff(new Item.Properties()));
    public static final RegistryObject<Item> POINTER = ITEMS.register("pointer", () -> new PointerItem(new Item.Properties().durability(1)));

    //EGG
    public static final RegistryObject<Item> STEVE_EGG = ITEMS.register("steve_spawn", () -> new ForgeSpawnEggItem(DMEntityType.OLD_STEVE, 0xffffff, 0xffffff,new Item.Properties().tab(DMCreativeTabs.ENTITIES)));
    public static final RegistryObject<Item> CYBERMAN_EGG = ITEMS.register("cyberman_spawn", () -> new ForgeSpawnEggItem(DMEntityType.CYBERMAN, 0xffffff, 0xffffff,new Item.Properties().tab(DMCreativeTabs.ENTITIES)));
    public static final RegistryObject<Item> CYBERBOSS_EGG = ITEMS.register("cyberboss_spawn", () -> new ForgeSpawnEggItem(DMEntityType.CYBERBOSS, 0xffffff, 0xffffff,new Item.Properties().tab(DMCreativeTabs.ENTITIES)));
    public static final RegistryObject<Item> CYBERHUMAN_EGG = ITEMS.register("cyberhuman_spawn", () -> new ForgeSpawnEggItem(DMEntityType.CYBERHUMAN, 0xffffff, 0xffffff,new Item.Properties().tab(DMCreativeTabs.ENTITIES)));
    public static final RegistryObject<Item> CYBERHUMAN_2_EGG = ITEMS.register("cyberhuman_2_spawn", () -> new ForgeSpawnEggItem(DMEntityType.CYBERHUMAN_2, 0xffffff, 0xffffff,new Item.Properties().tab(DMCreativeTabs.ENTITIES)));
    public static final RegistryObject<Item> CYBERHUMAN_3_EGG = ITEMS.register("cyberhuman_3_spawn", () -> new ForgeSpawnEggItem(DMEntityType.CYBERHUMAN_3, 0xffffff, 0xffffff,new Item.Properties().tab(DMCreativeTabs.ENTITIES)));
    public static final RegistryObject<Item> DALEK_EGG = ITEMS.register("dalek_spawn", () -> new ForgeSpawnEggItem(DMEntityType.DALEK, 0xffffff, 0xffffff,new Item.Properties().tab(DMCreativeTabs.ENTITIES)));
    public static final RegistryObject<Item> SWDALEK_EGG = ITEMS.register("swdalek_spawn", () -> new ForgeSpawnEggItem(DMEntityType.SWDALEK, 0xffffff, 0xffffff,new Item.Properties().tab(DMCreativeTabs.ENTITIES)));
    //public static final RegistryObject<Item> ADIPOSE_EGG = ITEMS.register("adipose_spawn", () -> new ForgeSpawnEggItem(DMEntityType.ADIPOSE, 0xffffff, 0xffffff,new Item.Properties().tab(DMCreativeTabs.ENTITIES)));

    //LASER
    public static final RegistryObject<Item> CYBER_LASER = ITEMS.register("cyber_laser", () -> new LaserItem(new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> DALEK_LASER = ITEMS.register("dalek_laser", () -> new DalekLaserItem(new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> RPG_LASER = ITEMS.register("rpg_laser", () -> new RPGItem(new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> MAGIC_LASER = ITEMS.register("magic_ammo", () -> new MagicLaserItem(new Item.Properties().tab(DMCreativeTabs.TOOLS)));
    public static final RegistryObject<Item> CLASSIC_DALEK_LASER = ITEMS.register("classic_dalek_laser", () -> new ClassicDalekLaserItem(new Item.Properties().tab(DMCreativeTabs.TOOLS)));

    //GUN
    public static final RegistryObject<Item> CYBER_GUN = ITEMS.register("cyber_gun", () -> new CyberGun(new Item.Properties().tab(DMCreativeTabs.TOOLS).durability(2500)));
    public static final RegistryObject<Item> DALEK_GUN = ITEMS.register("dalek_gun", () -> new DalekGun(new Item.Properties().tab(DMCreativeTabs.TOOLS).durability(2500)));
    public static final RegistryObject<Item> RPG_GUN = ITEMS.register("rpg_gun", () -> new RPGGun(new Item.Properties().tab(DMCreativeTabs.TOOLS).durability(2500)));
    public static final RegistryObject<Item> MAGIC_WAND = ITEMS.register("magic_wand", () -> new MagicWand(new Item.Properties().durability(2500)));
    public static final RegistryObject<Item> CLASSIC_DALEK_GUN = ITEMS.register("classic_dalek_gun", () -> new ClassicDalekGun(new Item.Properties().durability(2500)));

    //COMPUTER
    //public static final RegistryObject<Item> CPU = ITEMS.register("cpu", () -> new Item(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    //public static final RegistryObject<Item> GPU = ITEMS.register("gpu", () -> new GraphicsUnit(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
   // public static final RegistryObject<Item> RAM = ITEMS.register("ram", () -> new RAM(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
   // public static final RegistryObject<Item> MOTHERBOARD = ITEMS.register("motherboard", () -> new Motherboard(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
   // public static final RegistryObject<Item> VENTIRAD = ITEMS.register("ventirad", () -> new Ventirad(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    //public static final RegistryObject<Item> DISK = ITEMS.register("disk", () -> new Disks(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    //public static final RegistryObject<Item> ALIMENTATION = ITEMS.register("alimentation", () -> new Alimentation(new Item.Properties().durability(2500).tab(DMCreativeTabs.COMPUTER)));
    //public static final RegistryObject<Item> HDMI_CABLE = ITEMS.register("hdmi_cable", () -> new HDMICable(new Item.Properties().tab(DMCreativeTabs.COMPUTER)));

    public static final RegistryObject<Item> ROUND_THING_FRAME = ITEMS.register("round_thing_frame", () -> new Item((new Item.Properties()).tab(DMCreativeTabs.ROUNDEL)));
    public static final RegistryObject<Item> RED_ROUND_THING_FRAME = ITEMS.register("red_round_thing_frame", () -> new Item((new Item.Properties()).tab(DMCreativeTabs.ROUNDEL)));
    public static final RegistryObject<Item> BLUE_ROUND_THING_FRAME = ITEMS.register("blue_round_thing_frame", () -> new Item((new Item.Properties()).tab(DMCreativeTabs.ROUNDEL)));
    public static final RegistryObject<Item> GREEN_ROUND_THING_FRAME = ITEMS.register("green_round_thing_frame", () -> new Item((new Item.Properties()).tab(DMCreativeTabs.ROUNDEL)));
    public static final RegistryObject<Item> YELLOW_ROUND_THING_FRAME = ITEMS.register("yellow_round_thing_frame", () -> new Item((new Item.Properties()).tab(DMCreativeTabs.ROUNDEL)));
    public static final RegistryObject<Item> DARK_ROUND_THING_FRAME = ITEMS.register("dark_round_thing_frame", () -> new Item((new Item.Properties()).tab(DMCreativeTabs.ROUNDEL)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
