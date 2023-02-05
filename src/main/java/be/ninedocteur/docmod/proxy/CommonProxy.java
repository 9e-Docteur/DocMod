package be.ninedocteur.docmod.proxy;

import be.ninedocteur.docmod.common.entity.DMEntityType;
import be.ninedocteur.docmod.common.entity.mob.CybermanEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonProxy {

    public static void commonSetup(FMLCommonSetupEvent event){
        SpawnPlacements.register(DMEntityType.CYBERMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG, CybermanEntity::canSpawn);
    }
}
