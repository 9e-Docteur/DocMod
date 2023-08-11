package be.ninedocteur.docmod.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.Set;
import java.util.function.Supplier;

public class SyncDimensionListPacket {
    public ResourceKey<Level> level;
    public boolean add = true;

    public SyncDimensionListPacket(ResourceKey<Level> level, boolean add) {
        this.add = add;
        this.level = level;
    }

    public SyncDimensionListPacket(FriendlyByteBuf buffer) {
        this(buffer.readResourceKey(Registries.DIMENSION), buffer.readBoolean());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeResourceKey(level);
        buffer.writeBoolean(add);
    }

    public boolean handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            if(minecraft.player == null || minecraft.player.connection.levels() == null) {
                return;
            }
            Set<ResourceKey<Level>> levels = minecraft.player.connection.levels();
            if(levels.contains(level) && !add) {
                levels.remove(level);
            }else if(add){
                levels.add(level);
            }
        });
        return true;
    }
}
