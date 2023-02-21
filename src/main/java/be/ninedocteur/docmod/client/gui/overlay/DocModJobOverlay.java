package be.ninedocteur.docmod.client.gui.overlay;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.util.Constants;
import be.ninedocteur.docmod.utils.ColorUtils;
import be.ninedocteur.docmod.utils.DMMath;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.LaunchUtils;
import com.mojang.blaze3d.platform.GlUtil;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;

@OnlyIn(Dist.CLIENT)
public class DocModJobOverlay {
    private static int xp;
    private static Constants.Job job;
    public static boolean show = false;
    public static int currentTick = DMMath.convertSecondsToTicks(5);

    public static void render(RenderGuiOverlayEvent.Pre event){
        if(show){
            int w = event.getWindow().getGuiScaledWidth();
            int posX = 4;
            int posY = 4;
            Minecraft.getInstance().font.draw(event.getPoseStack(), "You have gained " + xp + " to the job " + job, posX, posY, ColorUtils.getWhite());
            tick();
        }
    }

    public static void setShow(boolean show, int xpGained, Constants.Job forJob) {
        DocModJobOverlay.show = show;
        currentTick = DMMath.convertSecondsToTicks(5);
        DocModJobOverlay.xp = xpGained;
        DocModJobOverlay.job = forJob;
    }

    public static void tick(){
        currentTick--;
        if(currentTick < 0){
            show = false;
        }
    }

}
