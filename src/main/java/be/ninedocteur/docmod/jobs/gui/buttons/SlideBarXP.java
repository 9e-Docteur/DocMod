package be.ninedocteur.docmod.jobs.gui.buttons;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.gui.screens.GuiHowXP;
import be.ninedocteur.docmod.jobs.util.JobsMath;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class SlideBarXP extends Button {

    private final ResourceLocation texture = new ResourceLocation(DocMod.MOD_ID + ":textures/gui/gui_unlocked_items.png");
    private final int xTexStart;
    private final int yTexStart;
    private final int yDiffText;
    private final GuiHowXP gui;
    private int buttonX = this.getX();
    private int buttonY = this.getY();

    protected static final net.minecraft.client.gui.components.Button.CreateNarration DEFAULT_NARRATION = (p_253298_) -> {
        return p_253298_.get();
    };

    public SlideBarXP(int posX, int posY, GuiHowXP gui)
    {
        super(posX, posY, 12, 15, Component.literal(""), new OnPressed(), DEFAULT_NARRATION);
        this.xTexStart = 70;
        this.yTexStart = 0;
        this.yDiffText = 15;
        this.gui = gui;
    }

    @Override
    public void render(PoseStack mStack, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            boolean hovered = mouseX >= this.buttonX && mouseY >= this.buttonY && mouseX < this.buttonX + this.width && mouseY < this.buttonY + this.height;
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, this.texture);
            int j = this.yTexStart;

            if(hovered)
            {
                j += this.yDiffText;
                if(Minecraft.getInstance().mouseHandler.isLeftPressed())
                {
                    this.buttonY = JobsMath.clamp(mouseY-7, this.gui.top + 29, this.gui.top + 29 + 91);
                    this.gui.page = (int) ((float)this.gui.pageNumber * ((float) (this.buttonY - (this.gui.top + 29))/ (float) 91));
                }
            }
            this.blit(mStack, this.buttonX, this.buttonY, this.xTexStart, j, 12, 15); //Icon
        }
    }
    
    public static class OnPressed implements OnPress{
    	@Override
		public void onPress(Button btn) {}
    }

}

