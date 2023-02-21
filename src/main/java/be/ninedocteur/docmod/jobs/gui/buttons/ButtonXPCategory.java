package be.ninedocteur.docmod.jobs.gui.buttons;


import java.awt.Color;


import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.gui.screens.GuiHowXP;
import be.ninedocteur.docmod.jobs.gui.screens.GuiJobInfos;
import be.ninedocteur.docmod.jobs.util.Constants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ButtonXPCategory extends Button {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(DocMod.MOD_ID, "textures/gui/gui_job_infos.png");
    private final int xTexStart;
    private final int yTexStart;
    private final Constants.XPCategories category;
    private final GuiJobInfos parent;
    private int buttonX = this.getX();
    private int buttonY = this.getY();

    protected static final net.minecraft.client.gui.components.Button.CreateNarration DEFAULT_NARRATION = (p_253298_) -> {
        return p_253298_.get();
    };

    public ButtonXPCategory(int x, int y, Constants.XPCategories categories, GuiJobInfos parent)
    {
        super(x, y, 80, 16, Component.literal(""), new OnPressed(), DEFAULT_NARRATION);
        this.category = categories;
        this.xTexStart = 16 * this.category.index;
        this.yTexStart = this.category.isCategory ? 196 : 180;
        this.parent = parent;
    }

    public void setPosition(int xPos, int yPos)
    {
        this.buttonX = xPos;
        this.buttonY = yPos;
    }
    
    public void renderButton(PoseStack mStack, int mouseX, int mouseY, float partialTicks)
    {
    	if (this.visible)
        {
            boolean hovered = mouseX >= this.buttonX && mouseY >= this.buttonY && mouseX < this.buttonX + this.width && mouseY < this.buttonY + this.height;
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, BACKGROUND);
            int i = this.xTexStart;
            int j = this.yTexStart;

            if(hovered) RenderSystem.setShaderColor(0.8F, 0.8F, 0.8F, 1.0F);
            else RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            this.blit(mStack, this.buttonX, this.buttonY, i, j, 16, 16);
            String txt = I18n.get("category." + this.category.name());
            int txtWidth = Minecraft.getInstance().font.width(txt);
            Minecraft.getInstance().font.draw(mStack, txt, this.buttonX + 48 - txtWidth/2, this.buttonY + 5, Color.BLACK.getRGB());
        }
    }
    
    public static class OnPressed implements OnPress{

		@Override
		public void onPress(Button btn) 
		{
			if(!(btn instanceof ButtonXPCategory)) return;
			ButtonXPCategory button = (ButtonXPCategory)btn;
			if(button.category == Constants.XPCategories.UNLOCK)
			{
				button.parent.offsetUnlock = button.parent.offsetUnlock == 0 ? -70 : 0;
				button.parent.update();
			}
			else if(button.category == Constants.XPCategories.XP)
				Minecraft.getInstance().setScreen(new GuiHowXP(button.parent.job));
		}
    	
    }
}
