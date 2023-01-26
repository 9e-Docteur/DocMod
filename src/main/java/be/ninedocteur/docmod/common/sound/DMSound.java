package be.ninedocteur.docmod.common.sound;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.utils.DMUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMSound {
    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DMUtils.MOD_ID);

    public static final RegistryObject<SoundEvent> CYBERMAN_AMBIENT = registerSoundEvent("entity.cyberman.ambient");
    public static final RegistryObject<SoundEvent> CYBERMAN_HURT = registerSoundEvent("entity.cyberman.hurt");
    public static final RegistryObject<SoundEvent> OLDSTEVE_HURT = registerSoundEvent("entity.oldsteve.hurt");
    public static final RegistryObject<SoundEvent> DALEK_GUN = registerSoundEvent("item.dalek_gun.shoot");
    public static final RegistryObject<SoundEvent> CYBERMAN_GUN = registerSoundEvent("item.cyberman_gun.shoot");
    public static final RegistryObject<SoundEvent> CYBERMAN_STEP = registerSoundEvent("entity.cyberman.step");
    public static final RegistryObject<SoundEvent> SONIC = registerSoundEvent("item.sonic.use");
    public static final RegistryObject<SoundEvent> RPG = registerSoundEvent("gun.rpg.shoot");
    public static final RegistryObject<SoundEvent> DALEK_AMBIANT = registerSoundEvent("entity.dalek.ambiant");
    public static final RegistryObject<SoundEvent> GARATIM_SONIC = registerSoundEvent("item.garatim_sonic.use");
    public static final RegistryObject<SoundEvent> MAGIC_WAND = registerSoundEvent("item.magic_wand.shoot");
    public static final RegistryObject<SoundEvent> LEVER_ON = registerSoundEvent("block.lever.on");
    public static final RegistryObject<SoundEvent> LEVER_OFF = registerSoundEvent("block.lever.off");
    public static final RegistryObject<SoundEvent> SPECIAL_WEAPON = registerSoundEvent("gun.dalek.special_weapon");
    public static final RegistryObject<SoundEvent> TARDIS_DEMAT = registerSoundEvent("block.tardis.demat");
    public static final RegistryObject<SoundEvent> TARDIS_REMAT = registerSoundEvent("block.tardis.remat");
    public static final RegistryObject<SoundEvent> REFUSED_TAKEOFF = registerSoundEvent("block.tardis.refused");
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(DocMod.MOD_ID, name), 16.0F));
    }
    public static void registerSounds(){
        IEventBus eventBus = DocMod.bus;
        SOUNDS.register(eventBus);
    }
}
