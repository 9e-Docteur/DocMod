package be.ninedocteur.docmod.registry;

import net.minecraft.resources.ResourceLocation;

public interface IRegistrable<T> {
    T setRegistryName(ResourceLocation regName);
    ResourceLocation getRegistryName();
}
