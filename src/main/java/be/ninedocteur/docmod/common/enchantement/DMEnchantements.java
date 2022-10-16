package be.ninedocteur.docmod.common.enchantement;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMEnchantements {
    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, DocMod.MOD_ID);

    public static RegistryObject<Enchantment> COOLDOWN_REDUCER = ENCHANTMENTS.register("cooldown_reducer", () ->
            new CooldownReducerEnchant()
    );

    public static RegistryObject<Enchantment> STRIKE = ENCHANTMENTS.register("strike", () ->
            new StrikeEnchant()
    );

    public static void register(IEventBus eventBus){
        ENCHANTMENTS.register(eventBus);
    }

}
