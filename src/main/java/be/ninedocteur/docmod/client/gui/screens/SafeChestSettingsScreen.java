package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.tileentity.SafeChestTileEntity;
import be.ninedocteur.docmod.utils.PlayerUtils;
import be.ninedocteur.docteam.Servers;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public class SafeChestSettingsScreen extends Screen {
    private static final ResourceLocation SETTINGS_TEXTURE = new ResourceLocation(DocMod.MOD_ID, "textures/gui/container/safechest/settings_menu.png");
    private SafeChestTileEntity safeChestTileEntity;
    private EditBox editBox;

    public SafeChestSettingsScreen(Component p_96550_, SafeChestTileEntity safeChestTileEntity) {
        super(p_96550_);
        this.safeChestTileEntity = safeChestTileEntity;
    }

    @Override
    protected void init() {
        super.init();
        editBox = new EditBox(this.font, this.width / 2 - 110, this.height / 2 + 60, 220, 20, Component.literal(""));
        this.addRenderableWidget(Button.builder(Component.translatable("docmod.gui.add"), (p_96781_) -> {
            safeChestTileEntity.addAuthorisedPeople(safeChestTileEntity.getUUIDByName(editBox.getValue()));
        }).bounds(this.width / 2 - 100, this.height + 20, 100, 20).build());
        this.addRenderableWidget(Button.builder(Component.translatable("docmod.gui.remove"), (p_96781_) -> {
            safeChestTileEntity.removeAuthorisedPeople(safeChestTileEntity.getUUIDByName(editBox.getValue()));
        }).bounds(this.width / 2 - 100, this.height - 20, 100, 20).build());
        this.addRenderableWidget(editBox);
    }

    @Override
    public void render(PoseStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
        super.render(p_96562_, p_96563_, p_96564_, p_96565_);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, SETTINGS_TEXTURE);
        int x = this.width / 2;
        int y = this.height / 2;
        this.blit(p_96562_, x, y, 0, 0, this.width, this.height);
        for(int i = 0; i < safeChestTileEntity.getAuthorisedPeople().size(); i++){
            int spaceBetween = 2;
            int headY = 127;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, PlayerUtils.getPlayerHead(safeChestTileEntity.getNameByUUID(safeChestTileEntity.getAuthorisedPeople().get(i))));
            if(i <= 6){
                headY = 110;
            }
            this.blit(p_96562_, i + spaceBetween + 16, headY, 0, 0, 16, 16);
        }
    }
}
