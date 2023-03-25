package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.tileentity.HologramTileEntity;
import be.ninedocteur.docmod.utils.PlanetUtils;
import be.ninedocteur.docmod.utils.PlayerUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class DMHologramBlock extends Screen {
    private EditBox userNameEntry;
    private Button saveBtn;
    private HologramTileEntity hologramTileEntity;

    public DMHologramBlock(HologramTileEntity hologramTileEntity) {
        super(Component.literal(""));
        this.hologramTileEntity = hologramTileEntity;
    }

    @Override
    protected void init() {
        super.init();
        int j = this.height / 4 + 48;
        saveBtn = Button.builder(Component.literal("Save"), (p_96781_) -> {
            try{
                getHologramTileEntity().setOwner(PlayerUtils.getUserUUID(userNameEntry.getValue()));
                getHologramTileEntity().sendUpdate();
            } catch (IllegalArgumentException e){
                e.printStackTrace();
                Minecraft.getInstance().player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Player not found!"));
            }
        }).bounds(this.width / 2 - 100, j + 24 * -1, 20, 20).build();
        this.addRenderableWidget(saveBtn);

        userNameEntry = new EditBox(this.font, this.width / 2 - 110, this.height / 2 + 60, 220, 20, Component.literal(""));
        addRenderableWidget(userNameEntry);
        drawString(new PoseStack(), this.font, "Current hologram :" + PlayerUtils.getUserNameByUUID(getHologramTileEntity().getPlayerTexture()), userNameEntry.getX(), userNameEntry.getY() - 30, -1);
    }

    @Override
    public void render(PoseStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
        super.render(p_96562_, p_96563_, p_96564_, p_96565_);
    }

    public EditBox getUserNameEntry() {
        return userNameEntry;
    }

    public String getUserNameEntryValue() {
        return userNameEntry.getValue();
    }

    public void setUserNameEntry(EditBox userNameEntry) {
        this.userNameEntry = userNameEntry;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(Button saveBtn) {
        this.saveBtn = saveBtn;
    }

    public HologramTileEntity getHologramTileEntity() {
        return hologramTileEntity;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
