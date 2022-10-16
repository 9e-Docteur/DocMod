package be.ninedocteur.docmod.client;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;

import javax.annotation.Nullable;
import java.util.function.Function;

public class BaseItemShower {

    private final SpawnData nextSpawnData = new SpawnData();
    @Nullable
    private Entity displayEntity;


    @Nullable
    public Entity getOrCreateDisplayEntity(Level pLevel) {
        if (this.displayEntity == null) {
            this.displayEntity = EntityType.loadEntityRecursive(this.nextSpawnData.getEntityToSpawn(), pLevel, Function.identity());
            if (this.nextSpawnData.getEntityToSpawn().size() == 1 && this.nextSpawnData.getEntityToSpawn().contains("id", 8) && this.displayEntity instanceof Mob) {
            }
        }

        return this.displayEntity;
    }
}
