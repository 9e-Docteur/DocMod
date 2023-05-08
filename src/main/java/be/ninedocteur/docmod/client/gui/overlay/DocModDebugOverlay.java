package be.ninedocteur.docmod.client.gui.overlay;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DMSharedConstants;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.utils.ColorUtils;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.LaunchUtils;
import com.google.common.base.Strings;
import com.mojang.blaze3d.platform.GlUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import org.lwjgl.system.windows.POINT;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

import static com.ibm.icu.text.PluralRules.Operand.*;

@OnlyIn(Dist.CLIENT)
public class DocModDebugOverlay extends GuiComponent {
    public static boolean showDebugOverlay = false;
    static List<String> list = new ArrayList();
    static List<String> rightList = new ArrayList();

    public static void render(RenderGuiOverlayEvent.Pre event){
        if (showDebugOverlay) {
            int w = event.getWindow().getGuiScaledWidth();
            int posX = 4;
            int posY = 4;
            //list.add("DocMod " + DMUtils.VERSION + " " + getBranch() + " Snapshot " + DMSharedConstants.getVersion());
            //list.add(getFPS(Minecraft.getInstance()) + " FPS");
            //rightList.add("CPU: " + GlUtil.getCpuInfo());
            //rightList.add("GPU: " + GlUtil.getRenderer());
//            for(int i = 0; i < list.size(); ++i) {
//                String s = list.get(i);
//                if (!Strings.isNullOrEmpty(s)) {
//                    int j = 9;
//                    int k = Minecraft.getInstance().font.width(s);
//                    int l = Minecraft.getInstance().getWindow().getGuiScaledWidth() - 2 + k;
//                    int i1 = 2 + j * i;
//                    Screen.fill(new PoseStack(), l - 1, i1 - 1, l - k + 1, i1 + j - 1, -1873784752);
//                    Minecraft.getInstance().font.draw(new PoseStack(), s, (float)l, (float)i1, 14737632);
//                }
//            }
//
//            for(int i = 0; i < rightList.size(); ++i) {
//                String s = rightList.get(i);
//                if (!Strings.isNullOrEmpty(s)) {
//                    int j = 9;
//                    int k = Minecraft.getInstance().font.width(s);
//                    int l = Minecraft.getInstance().getWindow().getGuiScaledWidth() - 2 - k;
//                    int i1 = 2 + j * i;
//                    Screen.fill(new PoseStack(), l - 1, i1 - 1, l + k + 1, i1 + j - 1, -1873784752);
//                    Minecraft.getInstance().font.draw(new PoseStack(), s, (float)l, (float)i1, 14737632);
//                }
//            }
            
            Minecraft.getInstance().font.draw(event.getPoseStack(), "DocMod " + DMUtils.VERSION + "" + " Snapshot " + DMSharedConstants.getVersion(), posX, posY, -1);
            Minecraft.getInstance().font.draw(event.getPoseStack(),getFPS(Minecraft.getInstance()) + " FPS", posX, posY + 10, -1);

            int cpuSide = sideX(event, "CPU:" + GlUtil.getCpuInfo());
            int gpuSide = sideX(event, "GPU:" + GlUtil.getRenderer());
            Minecraft.getInstance().font.draw(event.getPoseStack(), "CPU: " + GlUtil.getCpuInfo(), cpuSide, posY, -1);
            //RenderSystem.setShaderColor(1f, 1f, 1f, 0.35f);
            //Minecraft.getInstance().screen.fill(event.getPoseStack(), 1, 10, cpuSide, posY, -1873784752);
            Minecraft.getInstance().font.draw(event.getPoseStack(), "GPU: " + GlUtil.getRenderer(), gpuSide, posY + 10, -1);
            //RenderSystem.setShaderColor(1f, 1f, 1f, 0.35f);
            //Minecraft.getInstance().screen.fill(event.getPoseStack(), 1, 10, gpuSide, posY + 10, -1873784752);
        }
    }

    private static int sideX(RenderGuiOverlayEvent.Pre event, String content){
        int sideX = event.getWindow().getGuiScaledWidth() - Minecraft.getInstance().font.width(content) - 6;
        return sideX;
    }

    private static String getBranch(){
        if(LaunchUtils.isRunningInDev()){
            return "(" + SharedConstants.getCurrentVersion().getName() + "/dev)";
        } else {
            return "(" + SharedConstants.getCurrentVersion().getName() + "/release)";
        }
    }
    
    private static String getFPS(Minecraft mc) {

        return formatText(mc.fpsString.split("\\s+")[0]);

    }
    
    
    private static int getColoredFPS(Minecraft mc) {
    	if(Integer.valueOf(getFPS(mc)) < 29) {
    		return ColorUtils.getRed();
    	} else if(Integer.valueOf(getFPS(mc)) < 50){
    		return ColorUtils.getOrange();
    	} else {
    		return ColorUtils.getGreen();
    	}
    }
    
    private static String formatText(String text) {

        Component fpsString = Component.literal(text);

        return fpsString.getString();

    }
}
