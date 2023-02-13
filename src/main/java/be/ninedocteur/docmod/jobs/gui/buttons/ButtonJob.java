package be.ninedocteur.docmod.jobs.gui.buttons;

import be.ninedocteur.docmod.jobs.data.ClientJobsData;
import be.ninedocteur.docmod.jobs.util.GuiUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import be.ninedocteur.docmod.jobs.gui.screens.GuiJobInfos;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import org.lwjgl.opengl.GL11;

import java.awt.*;


public class ButtonJob extends Button {



    private final String title;
    private final String job;
    protected static final Button.CreateNarration DEFAULT_NARRATION = (p_253298_) -> {
        return p_253298_.get();
    };

    /**
     * Creates a Job Button
     * @param posX the x coordinate
     * @param posY the y coordinate
     * @param j the job the button is representing
     */
    public ButtonJob(int posX, int posY, String j) {
        super(posX, posY, 200, 40, Component.empty() ,new OnPressed(), DEFAULT_NARRATION);
        this.title = ClientJobsData.getJobName(j);
        this.job = j;
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
            float f = 1.0f;
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            GuiUtil.drawJobIcon(mStack, this, this.job, this.getX()+20, this.getY()+20, 32);
            long xp = ClientJobsData.playerJobs.getXPByJob(this.job);
            int lvl = ClientJobsData.playerJobs.getLevelByJob(this.job);
            long total = lvl >= ClientJobsData.JOBS_LEVELS.getMaxLevel(this.job) ? xp :
                    ClientJobsData.JOBS_LEVELS.getXPForLevel(this.job, lvl+1);
            GuiUtil.renderProgressBar(mStack, this, this.getX()+45, this.getY()+15, 150, 12, xp, total);
            drawName(mStack);
        }
    }

    /**
     * Renders the Job name and level on the screen
     * @param mStack
     */
    private void drawName(PoseStack mStack) {
        int lvl = ClientJobsData.playerJobs.getLevelByJob(this.job);
        String name = this.title + " (" + I18n.get("text.level") + " " + lvl + ")";
        int x = 120 - Minecraft.getInstance().font.width(name)/2;
        int y = Minecraft.getInstance().font.lineHeight/2;
        Minecraft.getInstance().font.draw(mStack, name, this.getX() + x, this.getY() + y, Color.black.getRGB());
    }
    
    public static class OnPressed implements net.minecraft.client.gui.components.Button.OnPress {

        /**
         * Opens a Jobs Infos GUI when the button is clicked
         * @param btn the button clicked
         */
        @Override
		public void onPress(Button btn) {
			if(!(btn instanceof ButtonJob))
                return;
            ButtonJob b = (ButtonJob)btn;
			Minecraft.getInstance().setScreen(new GuiJobInfos(b.job));
		}
    	
    }
}
