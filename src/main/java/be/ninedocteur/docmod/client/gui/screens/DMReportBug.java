package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.title.DMTitleScreen;
import be.ninedocteur.docmod.utils.DMUtils;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class DMReportBug extends PauseScreen {
    public DMReportBug() {
       super(false);
    }

    @SubscribeEvent
    public static void initScreen(final ScreenEvent.Init.Post event) {
        if(event.getScreen() instanceof PauseScreen) {
            final Window window = Minecraft.getInstance().getWindow();
            window.setTitle("DocMod " + DMUtils.VERSION);
            Button reportBug = Button.builder(Component.translatable("button." + DocMod.MOD_ID + ".report_bug"), (p_96781_) -> {
                DMUtils.openLink("https://github.com/9e-Docteur/docmod/issues");
            }).bounds(10, 10, 80, 20).build();
            event.getScreen().addRenderableWidget(reportBug);
        }
    }
}
