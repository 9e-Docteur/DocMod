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

    public static final RegistryObject<SoundEvent> CYBERMAN_AMBIENT = SOUNDS.register("entity.cyberman.ambient", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "entity.cyberman.ambient")));
    public static final RegistryObject<SoundEvent> CYBERMAN_HURT = SOUNDS.register("entity.cyberman.hurt", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "entity.cyberman.hurt")));
    public static final RegistryObject<SoundEvent> OLDSTEVE_HURT = SOUNDS.register("entity.oldsteve.hurt", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "entity.oldsteve.hurt")));
    public static final RegistryObject<SoundEvent> DALEK_GUN = SOUNDS.register("item.dalek_gun.shoot", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "item.dalek_gun.shoot")));
    public static final RegistryObject<SoundEvent> CYBERMAN_GUN = SOUNDS.register("item.cyberman_gun.shoot", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "item.cyberman_gun.shoot")));
    public static final RegistryObject<SoundEvent> CYBERMAN_STEP = SOUNDS.register("entity.cyberman.step", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "entity.cyberman.step")));
    public static final RegistryObject<SoundEvent> SONIC = SOUNDS.register("item.sonic.use", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "item.sonic.use")));
    public static final RegistryObject<SoundEvent> RPG = SOUNDS.register("gun.rpg.shoot", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "gun.rpg.shoot")));
    public static final RegistryObject<SoundEvent> DALEK_AMBIANT = SOUNDS.register("entity.dalek.ambiant", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "entity.dalek.ambiant")));
    public static final RegistryObject<SoundEvent> GARATIM_SONIC = SOUNDS.register("item.garatim_sonic.use", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "item.garatim_sonic.use")));
    public static final RegistryObject<SoundEvent> MAGIC_WAND = SOUNDS.register("item.magic_wand.shoot", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "item.magic_wand.shoot")));
    public static final RegistryObject<SoundEvent> LEVER_ON = SOUNDS.register("block.lever.on", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "block.lever.on")));
    public static final RegistryObject<SoundEvent> LEVER_OFF = SOUNDS.register("block.lever.off", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "block.lever.off")));
    public static final RegistryObject<SoundEvent> SPECIAL_WEAPON = SOUNDS.register("gun.dalek.special_weapon", () -> new SoundEvent(new ResourceLocation(DMUtils.MOD_ID, "gun.dalek.special_weapon")));

    public static void registerSounds(){
        IEventBus eventBus = DocMod.bus;
        SOUNDS.register(eventBus);
    }
}
