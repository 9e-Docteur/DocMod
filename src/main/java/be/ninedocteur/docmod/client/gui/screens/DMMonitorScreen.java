package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.TardisModel;
import be.ninedocteur.docmod.common.computer.BaseOS;
import be.ninedocteur.docmod.common.computer.OSRegistry;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import be.ninedocteur.docmod.registry.ModelRegistry;
import be.ninedocteur.docmod.utils.ColorUtils;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.ScreenUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.Model;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DMMonitorScreen extends Screen {
    public static ResourceLocation MONITOR_GUI = new ResourceLocation(DocMod.MOD_ID, "textures/gui/monitor_screen.png");

    private TardisTileEntity tardisTileEntity;

    public DMMonitorScreen(TardisTileEntity tardisTileEntity) {
        super(Component.literal("none"));
        this.tardisTileEntity = tardisTileEntity;
    }

    @Override
    protected void init() {
        this.addRenderableOnly(new Button(this.width - 345, this.height - 200, 80, 20, Component.translatable(tardisTileEntity.getOnOffString()), (p_96781_) -> {
            DocMod.LOGGER.info("TEST");
            if(tardisTileEntity.isOn()){
                tardisTileEntity.isOn = false;
            } else {
                tardisTileEntity.isOn = true;
            }
        }));
        super.init();
    }

    @Override
    public void render(PoseStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
        RenderSystem.setShaderTexture(0, MONITOR_GUI);
        blit(p_96562_, this.width - 345, this.height - 200, 0, 0, 256, 198);
        drawString(p_96562_, font, "Tardis Coords: " + tardisTileEntity.getBlockPos().getX() + " " + tardisTileEntity.getBlockPos().getY() + " " + tardisTileEntity.getBlockPos().getZ(), this.width - 330, this.height - 170, -1);
        if(tardisTileEntity.getTargetPosition() != null){
            drawString(p_96562_, font, "Destination Coords: " + tardisTileEntity.getTargetPosition().getX() + " " + tardisTileEntity.getTargetPosition().getY() + " " + tardisTileEntity.getTargetPosition().getZ(), this.width - 330, this.height - 160, -1);
            drawString(p_96562_, font, "Destination Dimension: " + tardisTileEntity.getTargetDimensionString(), this.width - 330, this.height - 150, -1);
        }
        Model model =  new TardisModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.NEW_Tardis));
        ScreenUtils.renderEntityModelOnScreen(p_96562_, this.width - 345, this.height - 200, 1, 1F, 1F, model, ModelRegistry.NEW_Tardis.getModel(), 1, 1, 1, 1);
        super.render(p_96562_, p_96563_, p_96564_, p_96565_);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
