package be.ninedocteur.docmod.common.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.entity.mob.*;
import be.ninedocteur.docmod.common.item.laser.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMEntityType {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DocMod.MOD_ID);

    public static final RegistryObject<EntityType<Zurbion>> ZURBION = ENTITY_TYPES.register("zurbion", () -> EntityType.Builder.of(Zurbion::new, MobCategory.MONSTER).sized(1f, 3f).build(new ResourceLocation(DocMod.MOD_ID, "zurbion").toString()));
    public static final RegistryObject<EntityType<Zurbitris>> ZURBITRIS = ENTITY_TYPES.register("zurbitris", () -> EntityType.Builder.of(Zurbitris::new, MobCategory.MONSTER).sized(1f, 3f).build(new ResourceLocation(DocMod.MOD_ID, "zurbitris").toString()));
    public static final RegistryObject<EntityType<OldSteve>> OLD_STEVE = ENTITY_TYPES.register("old_steve", () -> EntityType.Builder.of(OldSteve::new, MobCategory.CREATURE).sized(0.8f, 2f).build(new ResourceLocation(DocMod.MOD_ID, "old_steve").toString()));
    public static final RegistryObject<EntityType<Dalek>> DALEK = ENTITY_TYPES.register("dalek", () -> EntityType.Builder.of(Dalek::new, MobCategory.MONSTER).sized(0.8f, 2f).build(new ResourceLocation(DocMod.MOD_ID, "dalek").toString()));
    public static final RegistryObject<EntityType<CybermanEntity>> CYBERMAN = ENTITY_TYPES.register("cyberman", () -> EntityType.Builder.of(CybermanEntity::new, MobCategory.MONSTER).sized(1f, 2.35f).build(new ResourceLocation(DocMod.MOD_ID, "cyberman").toString()));
    public static final RegistryObject<EntityType<CyberHumanEntity>> CYBERHUMAN = ENTITY_TYPES.register("cyberhuman", () -> EntityType.Builder.of(CyberHumanEntity::new, MobCategory.MONSTER).sized(1f, 2.35f).build(new ResourceLocation(DocMod.MOD_ID, "cyberhuman").toString()));
    public static final RegistryObject<EntityType<CyberBossEntity>> CYBERBOSS = ENTITY_TYPES.register("cyberboss", () -> EntityType.Builder.of(CyberBossEntity::new, MobCategory.MONSTER).sized(1f, 2.35f).build(new ResourceLocation(DocMod.MOD_ID, "cyberboss").toString()));
    public static final RegistryObject<EntityType<CyberHumanEntitySecond>> CYBERHUMAN_2 = ENTITY_TYPES.register("cyberhuman_2", () -> EntityType.Builder.of(CyberHumanEntitySecond::new, MobCategory.MONSTER).sized(1f, 2.35f).build(new ResourceLocation(DocMod.MOD_ID, "cyberhuman_2").toString()));
    public static final RegistryObject<EntityType<CyberHumanEntityThird>> CYBERHUMAN_3 = ENTITY_TYPES.register("cyberhuman_3", () -> EntityType.Builder.of(CyberHumanEntityThird::new, MobCategory.MONSTER).sized(1f, 2.35f).build(new ResourceLocation(DocMod.MOD_ID, "cyberhuman_3").toString()));
    public static final RegistryObject<EntityType<SWDalek>> SWDALEK = ENTITY_TYPES.register("swdalek", () -> EntityType.Builder.of(SWDalek::new, MobCategory.MONSTER).sized(0.8f, 2f).build(new ResourceLocation(DocMod.MOD_ID, "swdalek").toString()));

    public static final RegistryObject<EntityType<Laser>> CYBER_LASER = ENTITY_TYPES.register("cyber_laser", () -> EntityType.Builder.<Laser>of(Laser::new, MobCategory.MISC).sized(0.5F, 0.5F).build(new ResourceLocation(DocMod.MOD_ID, "cyber_laser").toString()));
    public static final RegistryObject<EntityType<DalekLaser>> DALEK_LASER = ENTITY_TYPES.register("dalek_laser", () -> EntityType.Builder.<DalekLaser>of(DalekLaser::new, MobCategory.MISC).sized(0.5F, 0.5F).build(new ResourceLocation(DocMod.MOD_ID, "dalek_laser").toString()));
    public static final RegistryObject<EntityType<RPGLaser>> RPG_LASER = ENTITY_TYPES.register("rpg_laser", () -> EntityType.Builder.<RPGLaser>of(RPGLaser::new, MobCategory.MISC).sized(0.5F, 0.5F).build(new ResourceLocation(DocMod.MOD_ID, "dalek_laser").toString()));
    public static final RegistryObject<EntityType<MagicLaser>> WAND_LASER = ENTITY_TYPES.register("wand_laser", () -> EntityType.Builder.<MagicLaser>of(MagicLaser::new, MobCategory.MISC).sized(0.5F, 0.5F).build(new ResourceLocation(DocMod.MOD_ID, "dalek_laser").toString()));
    public static final RegistryObject<EntityType<ClassicDalekLaser>> CLASIC_DALEK_LASER = ENTITY_TYPES.register("classic_dalek_laser", () -> EntityType.Builder.<ClassicDalekLaser>of(ClassicDalekLaser::new, MobCategory.MISC).sized(0.5F, 0.5F).build(new ResourceLocation(DocMod.MOD_ID, "dalek_laser").toString()));
    public static final RegistryObject<EntityType<ClassicDalek>> CLASSIC_DALEK = ENTITY_TYPES.register("classic_dalek", () -> EntityType.Builder.of(ClassicDalek::new, MobCategory.MONSTER).sized(0.8f, 2f).build(new ResourceLocation(DocMod.MOD_ID, "classic_dalek").toString()));
    public static final RegistryObject<EntityType<AdiposeEntity>> ADIPOSE = ENTITY_TYPES.register("adipose", () -> EntityType.Builder.<AdiposeEntity>of(AdiposeEntity::new, MobCategory.CREATURE).sized(0.8f, 0.8f).build(new ResourceLocation(DocMod.MOD_ID, "adipose").toString()));
    public static final RegistryObject<EntityType<SittableEntity>> SEAT = ENTITY_TYPES.register("seat", () -> EntityType.Builder.<SittableEntity>of(SittableEntity::new, MobCategory.CREATURE).sized(0.8f, 0.8f).build(new ResourceLocation(DocMod.MOD_ID, "seat").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
