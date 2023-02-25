package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.menu.SafeChestMenu;
import be.ninedocteur.docmod.common.tileentity.SafeChestTileEntity;
import be.ninedocteur.docmod.utils.ColorUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.LanguageSelectScreen;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ShulkerBoxMenu;

import java.util.logging.Level;

public class SafeChestScreen extends AbstractContainerScreen<SafeChestMenu> {
    private boolean isOnSettingsMenu;
    private static final ResourceLocation CONTAINER_TEXTURE = new ResourceLocation(DocMod.MOD_ID, "textures/gui/container/safechest/menu.png");
    private static final ResourceLocation SETTINGS_TEXTURE = new ResourceLocation(DocMod.MOD_ID, "textures/gui/container/safechest/settings_menu.png");

    private static final ResourceLocation CHEST_BTN = new ResourceLocation(DocMod.MOD_ID, "textures/gui/container/safechest/button/chest.png");
    private static final ResourceLocation SETTINGS_BTN = new ResourceLocation(DocMod.MOD_ID, "textures/gui/container/safechest/button/settings.png");
    Button settings, chest;
    public SafeChestScreen(SafeChestMenu p_99240_, Inventory p_99241_, Component p_99242_) {
        super(p_99240_, p_99241_, p_99242_);
        ++this.imageHeight;
        isOnSettingsMenu = false;
    }

    @Override
    protected void init() {
        super.init();
        SafeChestTileEntity safeChestTileEntity = (SafeChestTileEntity) Minecraft.getInstance().level.getBlockEntity(menu.getPos());
            chest = Button.builder(Component.translatable("docmod.gui.safechest"), (p_213094_1_) -> {
                Minecraft.getInstance().setScreen(new SafeChestSettingsScreen(Component.empty(), safeChestTileEntity));
            }).bounds(this.width / 2 - 100, this.height / 2, 200, 20).build();
            this.addRenderableWidget(chest);
    }

    @Override
    public void render(PoseStack p_99249_, int p_99250_, int p_99251_, float p_99252_) {
        this.renderBackground(p_99249_);
        super.render(p_99249_, p_99250_, p_99251_, p_99252_);
        this.renderTooltip(p_99249_, p_99250_, p_99251_);
        this.renderButtons(p_99249_);
    }

    @Override
    protected void renderLabels(PoseStack p_97808_, int p_97809_, int p_97810_) {
        super.renderLabels(p_97808_, p_97809_, p_97810_);
        drawCenteredString(p_97808_, Minecraft.getInstance().font, "TestString", this.width / 2, this.height / 2, ColorUtils.getWhite());
    }

    protected void renderBg(PoseStack p_99244_, float p_99245_, int p_99246_, int p_99247_) {
        if(!isOnSettingsMenu) { //ON MAIN MENU
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, CONTAINER_TEXTURE);
            int i = (this.width - this.imageWidth) / 2;
            int j = (this.height - this.imageHeight) / 2;
            this.blit(p_99244_, i, j, 0, 0, this.imageWidth, this.imageHeight);
        } else {               //ON SCREEN MENU
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, SETTINGS_TEXTURE);
            int i = (this.width - this.imageWidth) / 2;
            int j = (this.height - this.imageHeight) / 2;
            this.blit(p_99244_, i, j, 0, 0, this.imageWidth, this.imageHeight);
        }
        this.renderButtons(p_99244_);
    }

    protected void renderButtons(PoseStack poseStack){
    }
}
