package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, DocMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> MAGIC_PARTICLES = PARTICLE_TYPES.register("magic_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CLASSIC_DALEK_PARTICLES = PARTICLE_TYPES.register("classic_dalek", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}
