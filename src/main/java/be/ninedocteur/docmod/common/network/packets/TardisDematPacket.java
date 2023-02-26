package be.ninedocteur.docmod.common.network.packets;

import be.ninedocteur.docmod.client.gui.menu.InfusionMenu;
import be.ninedocteur.docmod.common.tileentity.InfusionTableTileEntity;
import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TardisDematPacket {
    private BlockPos currentPos;
    private BlockPos destinationPos;
    private int tardisID;
    public TardisDematPacket(){}

    public TardisDematPacket(int tardisID, BlockPos currentPos, BlockPos destinationPos) {
        this.tardisID = tardisID;
        this.currentPos = currentPos;
        this.destinationPos = destinationPos;
    }

    public static TardisDematPacket fromBytes(FriendlyByteBuf buf)
    {
        return new TardisDematPacket(buf.readInt(), buf.readBlockPos(), buf.readBlockPos());
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(tardisID);
        buf.writeBlockPos(currentPos);
        buf.writeBlockPos(destinationPos);
    }

    public static void handle(TardisDematPacket packet, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(packet.currentPos) instanceof TardisTileEntity tardisTileEntity) {
                Level mLevel = context.getSender().getLevel();
                ChunkAccess chunk = mLevel.getChunk(packet.currentPos);
                if(mLevel instanceof ServerLevel serverLevel){
                    tardisTileEntity.setTargetPosition(packet.destinationPos);
                    tardisTileEntity.isDemating = true;
                    serverLevel.setChunkForced(chunk.getPos().x, chunk.getPos().z, true);
                    serverLevel.destroyBlock(tardisTileEntity.getCurrentLocation(), false);
                    serverLevel.getChunkSource().updateChunkForced(chunk.getPos(), true);
                }
                tardisTileEntity.isAlreadyDemat = true;
                tardisTileEntity.isDemating = false;
            }
        });
        context.setPacketHandled(true);
    }
}
