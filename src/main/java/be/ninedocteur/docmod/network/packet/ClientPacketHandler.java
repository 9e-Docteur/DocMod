package be.ninedocteur.docmod.network.packet;

import com.ibm.icu.text.Collator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Set;

public class ClientPacketHandler {
    public static void SyncDimensionList(SynchroniseDimensionPacket packet){
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        ResourceKey<Level> packetId = packet.getId();

        if(localPlayer == null || packetId == null){
            return;
        }

        Set<ResourceKey<Level>> world = localPlayer.connection.levels();
        if(world == null){
            return;
        }

        if(packet.getAdd()){
            world.add(packetId);
        } else {
            world.remove(packetId);
        }
    }
}
