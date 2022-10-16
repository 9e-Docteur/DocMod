package be.ninedocteur.docmod.client.gui.screens.compnent;

import com.google.common.collect.Lists;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DocModServerList {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Minecraft minecraft;
    private final List<ServerData> serverList = Lists.newArrayList();

    public DocModServerList(Minecraft pMinecraft) {
        this.minecraft = pMinecraft;
        this.load();
    }

    /**
     * Loads a list of servers from servers.dat, by running ServerData.getServerDataFromNBTCompound on each NBT compound
     * found in the "servers" tag list.
     */
    public void load() {
        try {
            this.serverList.clear();
            CompoundTag compoundtag = NbtIo.read(new File(this.minecraft.gameDirectory, "docmod-servers.dat"));
            if (compoundtag == null) {
                return;
            }

            ListTag listtag = compoundtag.getList("docmod-servers", 10);

            for(int i = 0; i < listtag.size(); ++i) {
                this.serverList.add(ServerData.read(listtag.getCompound(i)));
            }
        } catch (Exception exception) {
            LOGGER.error("Couldn't load server list", exception);
        }

    }

    /**
     * Runs getNBTCompound on each ServerData instance, puts everything into a "servers" NBT list and writes it to
     * servers.dat.
     */
    public void save() {
        try {
            ListTag listtag = new ListTag();

            for(ServerData serverdata : this.serverList) {
                listtag.add(serverdata.write());
            }

            CompoundTag compoundtag = new CompoundTag();
            compoundtag.put("docmod-servers", listtag);
            File file3 = File.createTempFile("docmod-servers", ".dat", this.minecraft.gameDirectory);
            NbtIo.write(compoundtag, file3);
            File file1 = new File(this.minecraft.gameDirectory, "servers.dat_old");
            File file2 = new File(this.minecraft.gameDirectory, "servers.dat");
            Util.safeReplaceFile(file2, file3, file1);
        } catch (Exception exception) {
            LOGGER.error("Couldn't save server list", exception);
        }

    }

    /**
     * Gets the ServerData instance stored for the given index in the list.
     */
    public ServerData get(int pIndex) {
        return this.serverList.get(pIndex);
    }

    public void remove(ServerData pServerData) {
        this.serverList.remove(pServerData);
    }

    /**
     * Adds the given ServerData instance to the list.
     */
    public void add(ServerData pServer) {
        this.serverList.add(pServer);
    }

    /**
     * Counts the number of ServerData instances in the list.
     */
    public int size() {
        return this.serverList.size();
    }

    /**
     * Takes two list indexes, and swaps their order around.
     */
    public void swap(int pPos1, int pPos2) {
        ServerData serverdata = this.get(pPos1);
        this.serverList.set(pPos1, this.get(pPos2));
        this.serverList.set(pPos2, serverdata);
        this.save();
    }

    public void replace(int pIndex, ServerData pServer) {
        this.serverList.set(pIndex, pServer);
    }

    public static void saveSingleServer(ServerData pServer) {
        DocModServerList serverlist = new DocModServerList(Minecraft.getInstance());
        serverlist.load();

        for(int i = 0; i < serverlist.size(); ++i) {
            ServerData serverdata = serverlist.get(i);
            if (serverdata.name.equals(pServer.name) && serverdata.ip.equals(pServer.ip)) {
                serverlist.replace(i, pServer);
                break;
            }
        }

        serverlist.save();
    }
}
