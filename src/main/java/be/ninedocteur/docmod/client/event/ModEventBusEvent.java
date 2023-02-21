package be.ninedocteur.docmod.client.event;


import be.ninedocteur.docmod.client.models.*;
import be.ninedocteur.docmod.client.models.entity.*;
import be.ninedocteur.docmod.client.render.*;
import be.ninedocteur.docmod.client.render.entity.*;
import be.ninedocteur.docmod.common.entity.DMEntityType;
import be.ninedocteur.docmod.common.entity.mob.*;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMParticles;
import be.ninedocteur.docmod.common.particle.ClassicDalekParticles;
import be.ninedocteur.docmod.common.particle.MagicParticles;
import be.ninedocteur.docmod.jobs.IJobFactory;
import be.ninedocteur.docmod.jobs.JobFactory;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import be.ninedocteur.docmod.registry.ModelRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PandaModel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DocMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvent {


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void modelRegistryEvent(EntityRenderersEvent.RegisterLayerDefinitions registerLayerDefinitions) {
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.MODEl, ToyotaRotorModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.Glass, GlassTubeModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.NEW_Tardis, TardisModel::cBL);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.ZurbTeleporter, ZurbTeleporterModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.Zurbion, ZurbionEntityModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.RedGlass, RedGlassTubeModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.GreenGlass, GreenGlassTubeModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.OrangeGlass, OrangeGlassTubeModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.ZURBION_LAYER_LOCATION, ZurbionEntityModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.ZURBITRIS_LAYER_LOCATION, ZurbitrisEntityModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.STEVE_LAYER_LOCATION, OldSteveEntityModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.DALEK_LAYER_LOCATION, DalekEntityModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.CYBERMAN_LAYER_LOCATION, CybermanEntityModel::createbodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.CYBERHUMAN_LAYER_LOCATION_VARIANT_1, CyberHumanModel::createbodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.ITEM_SHOWER, ItemShowerModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.PANDA_SKIN, PandaModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.CYBERHUMAN_LAYER_LOCATION_VARIANT_2, CyberHumanModelSecond::createbodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.CYBERBOSS_LAYER_LOCATION, CyberBossEntityModel::createbodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.CYBERHUMAN_LAYER_LOCATION_VARIANT_3, CyberHumanModelThird::createbodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.RED_TOYOTA, RedToyotaRotorModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.SWDALEK_LAYER_LOCATION, SpecialWeaponsDalekEntityModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.DAMAGED_DALEK_LAYER_LOCATION, DamagedDalekEntityModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(LayerDefinitionsRegistry.ADIPOSE, AdiposeModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.COMPUTER, ComputerModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.MONITOR, MonitorModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.CHAIR, ChairModel::createBodyLayer);
        registerLayerDefinitions.registerLayerDefinition(ModelRegistry.TARDIS_DOOR, TardisDoorModel::createBodyLayer);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DMEntityType.ZURBION.get(), ZurbionRenderer::new);
        event.registerEntityRenderer(DMEntityType.ZURBITRIS.get(), ZurbitrisRenderer::new);
        event.registerEntityRenderer(DMEntityType.OLD_STEVE.get(), OldSteveRenderer::new);
        event.registerEntityRenderer(DMEntityType.DALEK.get(), DalekRenderer::new);
        event.registerEntityRenderer(DMEntityType.CYBERMAN.get(), CybermanRenderer::new);
        event.registerEntityRenderer(DMEntityType.CYBERHUMAN.get(), CyberHumanRenderer::new);
        event.registerEntityRenderer(DMEntityType.CYBERBOSS.get(), CyberBossRenderer::new);
        event.registerEntityRenderer(DMEntityType.CYBERHUMAN_2.get(), CyberHumanRendererSecond::new);
        event.registerEntityRenderer(DMEntityType.CYBERHUMAN_3.get(), CyberHumanRendererThird::new);
        event.registerEntityRenderer(DMEntityType.CYBER_LASER.get(), CyberLaserRenderer::new);
        event.registerEntityRenderer(DMEntityType.DALEK_LASER.get(), DalekLaserRenderer::new);
        event.registerEntityRenderer(DMEntityType.RPG_LASER.get(), RPGLaserRenderer::new);
        event.registerEntityRenderer(DMEntityType.SWDALEK.get(), SpecialWeaponsDalekRenderer::new);
        event.registerEntityRenderer(DMEntityType.WAND_LASER.get(), WandLaserRenderer::new);
        event.registerEntityRenderer(DMEntityType.CLASSIC_DALEK.get(), ClassicDalekRenderer::new);
        event.registerEntityRenderer(DMEntityType.CLASIC_DALEK_LASER.get(), ClassicDalekLaserRenderer::new);
        event.registerEntityRenderer(DMEntityType.ADIPOSE.get(), AdiposeRenderer::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(DMEntityType.ZURBION.get(), Zurbion.createAttributes().build());
        event.put(DMEntityType.ZURBITRIS.get(), Zurbitris.createAttributes().build());
        event.put(DMEntityType.OLD_STEVE.get(), OldSteve.createAttributes().build());
        event.put(DMEntityType.DALEK.get(), Dalek.createAttributes().build());
        event.put(DMEntityType.CYBERMAN.get(), CybermanEntity.createAttributes().build());
        event.put(DMEntityType.CYBERHUMAN.get(), CyberHumanEntity.createAttributes().build());
        event.put(DMEntityType.CYBERHUMAN_2.get(), CyberHumanEntitySecond.createAttributes().build());
        event.put(DMEntityType.CYBERBOSS.get(), CyberBossEntity.createAttributes().build());
        event.put(DMEntityType.CYBERHUMAN_3.get(), CyberHumanEntityThird.createAttributes().build());
        event.put(DMEntityType.SWDALEK.get(), SWDalek.createAttributes().build());
        event.put(DMEntityType.CLASSIC_DALEK.get(), ClassicDalek.createAttributes().build());
        event.put(DMEntityType.ADIPOSE.get(), AdiposeEntity.createAttributes().build());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(DMParticles.MAGIC_PARTICLES.get(), MagicParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(DMParticles.CLASSIC_DALEK_PARTICLES.get(), ClassicDalekParticles.Provider::new);
    }
}
