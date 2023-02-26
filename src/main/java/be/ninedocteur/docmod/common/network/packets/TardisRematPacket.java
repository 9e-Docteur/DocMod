package be.ninedocteur.docmod.common.network.packets;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class TardisRematPacket {
    private BlockPos destinationPos;
    private int tardisID;
    private UUID ownerUUID;

    public TardisRematPacket(int tardisID, UUID ownerUUID, BlockPos destinationPos) {
        this.tardisID = tardisID;
        this.ownerUUID = ownerUUID;
        this.destinationPos = destinationPos;
    }

    public static TardisRematPacket fromBytes(FriendlyByteBuf buf)
    {
        return new TardisRematPacket(buf.readInt(), buf.readUUID(), buf.readBlockPos());
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(tardisID);
        buf.writeUUID(ownerUUID);
        buf.writeBlockPos(destinationPos);
    }

    //TODO: VERIF QUE LORS DU REMAT, LES INFOS COMME ID ETC SONT BIEN TRANSFÉRER DANS LE NOUVEAU BLOC
    public static void handle(TardisRematPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(packet.destinationPos) instanceof TardisTileEntity tardisTileEntityOLD) {
                Level mLevel = ctx.get().getSender().getLevel();
                ChunkAccess chunk = mLevel.getChunk(packet.destinationPos);
                if(mLevel instanceof ServerLevel serverLevel){
                    TardisTileEntity tardisTileEntity = DMTileEntity.Tardis.get().create(packet.destinationPos, tardisTileEntityOLD.getBlockState());
                    tardisTileEntity.setId(packet.tardisID);
                    tardisTileEntity.setOwnerUUID(packet.ownerUUID);
                    tardisTileEntity.setCurrentPosition(packet.destinationPos);
                    tardisTileEntity.setTargetPosition(packet.destinationPos);
                    tardisTileEntity.setTargetDimension(Level.OVERWORLD);
                    tardisTileEntity.tardisMap.put(packet.tardisID, tardisTileEntity);
                    serverLevel.setChunkForced(chunk.getPos().x, chunk.getPos().z, true);
                    serverLevel.getServer().getLevel(tardisTileEntity.getTargetDimension()).setBlockEntity(tardisTileEntity);
                    tardisTileEntity.setVisible(true);
                    serverLevel.getChunkSource().updateChunkForced(chunk.getPos(), true);
                    tardisTileEntity.isAlreadyDemat = true;
                    tardisTileEntity.isDemating = false;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
