package be.ninedocteur.docmod.utils;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;

public class EntityUtils {

    protected final SynchedEntityData entityData;
    private static final EntityDataAccessor<Boolean> DATA_CUSTOM_NAME_VISIBLE = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.BOOLEAN);


    private double attacksPerSecond = 1.0D;

    public EntityUtils(SynchedEntityData entityData) {
        this.entityData = entityData;
    }

    public boolean shouldShowName() {
        return this.entityData.get(DATA_CUSTOM_NAME_VISIBLE);
    }
}
