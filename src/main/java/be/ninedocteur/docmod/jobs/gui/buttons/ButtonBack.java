package be.ninedocteur.docmod.jobs.gui.buttons;

import be.ninedocteur.docmod.DocMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import be.ninedocteur.docmod.jobs.gui.screens.GuiHowXP;
import be.ninedocteur.docmod.jobs.gui.screens.GuiJobInfos;
import be.ninedocteur.docmod.jobs.gui.screens.MainJobsMenu;
import org.lwjgl.opengl.GL11;

public class ButtonBack extends Button {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(DocMod.MOD_ID, "textures/gui/gui_job_infos.png");
    private final int xTexStart;
    private final int yTexStart;
    private final int xDiffText;
    private Screen parent;
    protected static final Button.CreateNarration DEFAULT_NARRATION = (p_253298_) -> {
        return p_253298_.get();
    };

    /**
     * Creates a back arrow button
     * @param x the x coordinate
     * @param y the y coordinate
     * @param parent the parent GUI
     */
    public ButtonBack(int x, int y, Screen parent) {
        super(x, y, 18, 10, Component.empty(), new OnPressed(), DEFAULT_NARRATION);
        this.xTexStart = 0;
        this.yTexStart = 212;
        this.xDiffText = 18;
        this.parent = parent;
    }

    /**
     * Renders the widget on the screen
     * @param mStack
     * @param mouseX
     * @param mouseY
     * @param partialTicks
     */
    @Override
    public void renderButton(PoseStack mStack, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            boolean hovered = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, BACKGROUND);
            int i = this.xTexStart;
            int j = this.yTexStart;

            if (hovered)
                i += this.xDiffText;
            this.blit(mStack, this.getX(), this.getY(), i, j, this.width, this.height);
        }
    }
    
    public static class OnPressed implements OnPress{

        /**
         * Goes back to the previous GUI when the button is clicked
         * @param btn the button clicked
         */
        @Override
		public void onPress(Button btn) {
			if(!(btn instanceof ButtonBack))
                return;
			ButtonBack button = (ButtonBack)btn;
			if(button.parent instanceof GuiJobInfos)
				Minecraft.getInstance().setScreen(new MainJobsMenu());
			if(button.parent instanceof GuiHowXP)
				Minecraft.getInstance().setScreen(new GuiJobInfos(((GuiHowXP)button.parent).job));
		}
    	
    }
}
