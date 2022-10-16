package be.ninedocteur.docmod.registry;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map.Entry;

public class Registry<T> {
    private HashMap<ResourceLocation, T> registry = new HashMap<ResourceLocation, T>();

    public HashMap<ResourceLocation, T> getRegistry(){
        return this.registry;
    }

    public T getValue(ResourceLocation key) {
        return this.registry.get(key);
    }

    public ResourceLocation getKeyFromValue(T val) {
        for(Entry<ResourceLocation, T> entry : registry.entrySet()) {
            if(entry.getValue().equals(val))
                return entry.getKey();
        }
        return null;
    }

    public void register(String key, T value) {
        ResourceLocation resourceLocation = new ResourceLocation(DocMod.MOD_ID, key);
        if(value instanceof IRegistrable)
            ((IRegistrable<?>)value).setRegistryName(resourceLocation);
        this.registry.put(resourceLocation, value);
    }

    public void register(ResourceLocation key, T value) {
        if(value instanceof IRegistrable)
            ((IRegistrable<?>)value).setRegistryName(key);
        this.registry.put(key, value);

    }

}
