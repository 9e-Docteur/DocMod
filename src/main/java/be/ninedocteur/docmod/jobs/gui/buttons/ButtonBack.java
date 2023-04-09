package be.ninedocteur.docmod.jobs.gui.buttons;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.gui.screens.GuiHowXP;
import be.ninedocteur.docmod.jobs.gui.screens.GuiJobInfos;
import be.ninedocteur.docmod.jobs.gui.screens.MainJobsMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import org.lwjgl.opengl.GL11;


import net.minecraft.client.Minecraft;

@OnlyIn(Dist.CLIENT)
public class ButtonBack extends net.minecraft.client.gui.components.Button {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(DocMod.MOD_ID, "textures/gui/gui_job_infos.png");
    private final int xTexStart;
    private final int yTexStart;
    private final int xDiffText;
    private final Screen parent;
    private int buttonX = this.getX();
    private int buttonY = this.getY();

    protected static final net.minecraft.client.gui.components.Button.CreateNarration DEFAULT_NARRATION = (p_253298_) -> {
        return p_253298_.get();
    };

    public ButtonBack(int x, int y, Screen parent)
    {
        super(x, y, 18, 10, Component.literal(""), new OnPressed(), DEFAULT_NARRATION);
        this.xTexStart = 0;
        this.yTexStart = 212;
        this.xDiffText = 18;
        this.parent = parent;
    }

    public void setPosition(int xPos, int yPos)
    {
        buttonX = xPos;
        buttonY = yPos;
    }


    @Override
    public void render(PoseStack mStack, int mouseX, int mouseY, float p_93660_) {
    	 if (this.visible)
         {
             boolean hovered = mouseX >= this.buttonX && mouseY >= this.buttonY && mouseX < this.buttonX + this.width && mouseY < this.buttonY + this.height;
             RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
             RenderSystem.setShader(GameRenderer::getPositionTexShader);
             RenderSystem.setShaderTexture(0, BACKGROUND);
             int i = this.xTexStart;
             int j = this.yTexStart;

             if (hovered)
             {
                 i += this.xDiffText;
             }

             this.blit(mStack, this.buttonX, this.buttonY, i, j, this.width, this.height);
         }
    	super.render(mStack, mouseX, mouseY, p_93660_);
    }
    
    public static class OnPressed implements OnPress{

		@Override
		public void onPress(Button btn)
		{
			if(!(btn instanceof ButtonBack)) return;
			ButtonBack button = (ButtonBack)btn;
			if(button.parent instanceof GuiJobInfos)
				Minecraft.getInstance().setScreen(new MainJobsMenu());
			if(button.parent instanceof GuiHowXP)
				Minecraft.getInstance().setScreen(new GuiJobInfos(((GuiHowXP)button.parent).job));
		}
    	
    }
}
