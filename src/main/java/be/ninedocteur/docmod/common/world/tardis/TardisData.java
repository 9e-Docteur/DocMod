package be.ninedocteur.docmod.common.world.tardis;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.ArrayList;
import java.util.List;

public class TardisData extends SavedData {

    public final List<ResourceKey<Level>> tardisLevelKeys;

    public TardisData(){
        tardisLevelKeys = new ArrayList<>();
    }

    public TardisData(CompoundTag tag) {
        this();
        tardisLevelKeys.clear();
        ListTag list = tag.getList("data", Tag.TAG_STRING);
        for (Tag keyTag : list) {
            tardisLevelKeys.add(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(keyTag.getAsString())));
        }
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for(ResourceKey<Level> key: tardisLevelKeys){
            list.add(StringTag.valueOf(key.location().toString()));
        }
        tag.put("data", list);
        return tag;

    }

    public static void registerOldTardises(MinecraftServer server, List<ResourceKey<Level>> tardisKeys){
        for(ResourceKey<Level> level : tardisKeys){
            LevelCreator.getOrCreateLevel(server, level, LevelCreator::createStem);
        }
    }
}
