package be.ninedocteur.docmod.jobs.gui.buttons;


import be.ninedocteur.docmod.DocMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import be.ninedocteur.docmod.jobs.gui.screens.GuiHowXP;
import be.ninedocteur.docmod.jobs.gui.screens.GuiJobInfos;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;


public class ButtonXPCategory extends Button {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(DocMod.MOD_ID, "textures/gui/gui_job_infos.png");
    private final int xTexStart;
    private final int yTexStart;
    private final Type type;
    private final GuiJobInfos parent;
    protected static final Button.CreateNarration DEFAULT_NARRATION = (p_253298_) -> {
        return p_253298_.get();
    };

    /**
     * Creates the button
     * @param x the x coordinate
     * @param y the y coordinate
     * @param type the category type (XP / UNLOCK)
     * @param parent the parent GUI
     */
    public ButtonXPCategory(int x, int y, Type type, GuiJobInfos parent) {
        super(x, y, 80, 16, Component.empty(), new OnPressed(), DEFAULT_NARRATION);
        this.type = type;
        this.xTexStart = type == Type.XP ? 0 : 16;
        this.yTexStart = 196;
        this.parent = parent;
    }

    /**
     * Renders the widget on the screen
     * @param mStack
     * @param mouseX
     * @param mouseY
     * @param partialTicks
     */
    public void renderButton(PoseStack mStack, int mouseX, int mouseY, float partialTicks) {
    	if (this.visible) {
            boolean hovered = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, BACKGROUND);
            int i = this.xTexStart;
            int j = this.yTexStart;

            if(hovered) RenderSystem.setShaderColor(0.8F, 0.8F, 0.8F, 1.0F);
            else RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            this.blit(mStack, this.getX(), this.getY(), i, j, 16, 16);
            String name = "category." + type.name().toLowerCase();
            String txt = I18n.get(name);
            int txtWidth = Minecraft.getInstance().font.width(txt);
            Minecraft.getInstance().font.draw(mStack, txt, this.getX() + 48 - txtWidth/2.0F, this.getY() + 5, Color.BLACK.getRGB());
        }
    }
    
    public static class OnPressed implements OnPress{

        /**
         * Opens the How XP GUI or shows the Blocked Stacks when a button is clicked, depending on its type
         * @param btn the button clicked
         */
        @Override
		public void onPress(Button btn) {
			if(!(btn instanceof ButtonXPCategory))
                return;
			ButtonXPCategory button = (ButtonXPCategory)btn;
            switch(button.type){
                case XP:
                    Minecraft.getInstance().setScreen(new GuiHowXP(button.parent.job));
                case UNLOCK:
                    button.parent.offsetUnlock = button.parent.offsetUnlock == 0 ? -70 : 0;
                    button.parent.init();
            }
		}
    	
    }


    public enum Type{
        XP,
        UNLOCK;
    }
}
