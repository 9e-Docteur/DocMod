package be.ninedocteur.docmod.common.world.tardis;

import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;

import java.util.ArrayList;
import java.util.List;

public class TardisWorldManager {
    private static List<TardisTileEntity> TARDIS = new ArrayList<>();

    public static List<TardisTileEntity> getTardis() {
        return TARDIS;
    }

    public static void addTardis(TardisTileEntity tardisTileEntity){
        TARDIS.add(tardisTileEntity);
    }
}
