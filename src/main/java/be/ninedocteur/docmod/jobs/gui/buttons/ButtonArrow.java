package be.ninedocteur.docmod.jobs.gui.buttons;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.gui.screens.MainJobsMenu;
import be.ninedocteur.docmod.jobs.util.GuiUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ButtonArrow extends Button {

    public static final ResourceLocation BACKGROUND = new ResourceLocation(DocMod.MOD_ID, "textures/gui/main_menu.png");

    private final boolean isUp;
    private final MainJobsMenu gui;
    protected static final Button.CreateNarration DEFAULT_NARRATION = (p_253298_) -> {
        return p_253298_.get();
    };

    /**
     * Creates a button to move up or down the Main Jobs Menu
     * @param posX the x coordinate
     * @param posY the y coordinate
     * @param gui the Main Jobs Menu GUI
     * @param isUp sets the direction of the arrow to be up or down
     */
    public ButtonArrow(int posX, int posY, MainJobsMenu gui, boolean isUp) {
        super(posX, posY,  17, 10, Component.empty(), new OnPressed(), DEFAULT_NARRATION);
        this.gui = gui;
        this.isUp = isUp;
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
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bindForSetup(BACKGROUND);
            int x = this.isHovered ? 17 : 0;
            int y = isUp ? 220 : 230;
            GuiUtil.drawTexture(mStack, this, this.getX(), this.getY(), x, y, 17, 10);
        }
    }

    public static class OnPressed implements OnPress{

        /**
         * Scrolls the Main Jobs Menu up or down when the button is clicked, depending on the button's direction
         * @param btn the button clicked
         */
        @Override
        public void onPress(Button btn) {
            if(!(btn instanceof ButtonArrow))
                return;
            ButtonArrow b = (ButtonArrow) btn;
            double direction = b.isUp ? 1 : -1;
            b.gui.mouseScrolled(0, 0, direction);
        }
    }
}
