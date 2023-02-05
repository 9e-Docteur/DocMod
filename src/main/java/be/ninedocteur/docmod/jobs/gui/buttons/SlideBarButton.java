package be.ninedocteur.docmod.jobs.gui.buttons;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.util.JobsUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import be.ninedocteur.docmod.jobs.gui.screens.SliderParent;
import org.lwjgl.opengl.GL11;

public class SlideBarButton extends Button {

    private final ResourceLocation texture = new ResourceLocation(DocMod.MOD_ID + "textures/gui/gui_how_xp.png");
    private final SliderParent parent;

    public final boolean isVertical;
    public final int startPos;
    public final int endPos;


    /**
     * Creates a slide bar
     * @param startPos the first position of the slide bar on its main axis
     * @param endPos the last position of the slide bar on its main axis
     * @param otherPos the position of the slide bar on its second axis
     * @param parent the parent GUI
     * @param isVertical sets the main axis to be the Y-Axis if this is true, the X-Axis otherwise
     */
    public SlideBarButton(int startPos, int endPos, int otherPos, SliderParent parent, boolean isVertical) {
        super((isVertical ? otherPos : startPos), (isVertical ? startPos : otherPos),
                (isVertical ? 12 : 15), (isVertical ? 15 : 12),
                Component.empty(), new OnPressed(), DEFAULT_NARRATION);
        this.parent = parent;
        this.isVertical = isVertical;
        this.endPos = endPos;
        this.startPos = startPos;
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
            boolean disabled = this.parent.getLastPage(isVertical) == 0;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bindForSetup(this.texture);

            int textY = 0;
            int textX = isVertical ? 177 : 189;
            if(disabled)
                textY += this.isVertical ? 15 : 12;
            int width = isVertical ? 12 : 15;
            int height = isVertical ? 15 : 12;
            this.blit(mStack, this.getX(), this.getY(), textX, textY, width, height); //Slide bar
        }
    }

    /**
     * Updates the position of the slide bar across its axis
     * @param pos the new position (clamped between startPos and endPos)
     */
    private void updatePos(int pos){
        int p = JobsUtil.clamp(pos, startPos, endPos);
        int y = this.getY();
        int x = this.getX();
        if(isVertical)
            y = p;
        else
            x = p;
    }

    /**
     * Updates the position of the slide bar across its axis based on the page the parent is at
     */
    public void update(){
        float progress = (float)parent.getPage(isVertical)/(float)parent.getLastPage(isVertical);
        int pos = startPos + (int)((endPos-startPos)*progress);
        updatePos(pos);
    }

    public static class OnPressed implements OnPress{

        /**
         * Does nothing when pressed
         * @param btn the button pressed
         */
        @Override
		public void onPress(Button btn) {}    	
    }
}

