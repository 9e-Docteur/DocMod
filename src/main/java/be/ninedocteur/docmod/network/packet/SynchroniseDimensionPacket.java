package be.ninedocteur.docmod.network.packet;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Consumer;

public class SynchroniseDimensionPacket implements Consumer<NetworkEvent.Context> {
    private static final SynchroniseDimensionPacket invalid = new SynchroniseDimensionPacket(null, false);
    public static final Codec<SynchroniseDimensionPacket> codec = RecordCodecBuilder.create(instance -> instance.group(Level.RESOURCE_KEY_CODEC.optionalFieldOf("id", null).forGetter(SynchroniseDimensionPacket::getId), Codec.BOOL.fieldOf("add").forGetter(SynchroniseDimensionPacket::getAdd)).apply(instance, SynchroniseDimensionPacket::new));
    private final ResourceKey<Level> id;
    private final boolean add;

    public SynchroniseDimensionPacket(ResourceKey<Level> id, boolean add){
        this.id = id;
        this.add = add;
    }

    public ResourceKey<Level> getId(){
        return this.id;
    }

    @Override
    public void accept(NetworkEvent.Context context) {
        context.enqueueWork(() -> ClientPacketHandler.SyncDimensionList(this));
    }

    public boolean getAdd() {
        return this.add;
    }
}
