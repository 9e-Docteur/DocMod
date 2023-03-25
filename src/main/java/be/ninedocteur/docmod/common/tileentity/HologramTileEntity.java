package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public class HologramTileEntity extends BlockEntity {
    private UUID owner;
    private UUID playerTexture;
    public boolean isThereTextures;

    public HologramTileEntity(BlockPos pos, BlockState state) {
        super(DMTileEntity.HOLOGRAM.get(), pos, state);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putUUID("owner", this.owner);
        compoundTag.putString("texture", this.playerTexture.toString());
        compoundTag.putBoolean("txthere", this.isThereTextures);
        return super.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
        owner = nbt.getUUID("owner");
        playerTexture = nbt.getUUID("texture");
        isThereTextures = nbt.getBoolean("txthere");
    }

    public UUID getOwner() {
        return owner;
    }

    public UUID getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(UUID playerTexture) {
        this.playerTexture = playerTexture;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public void sendUpdate(){
        Minecraft.getInstance().player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Hologram updated!"));
        this.setChanged();
        isThereTextures = true;
    }
}
