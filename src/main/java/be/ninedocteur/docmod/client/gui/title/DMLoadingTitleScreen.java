package be.ninedocteur.docmod.client.gui.title;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.screens.DMLoginScreen;

import com.mojang.blaze3d.platform.Window;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.LaunchUtils;
import be.ninedocteur.docteam.api.DocTeamAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class DMLoadingTitleScreen extends TitleScreen{
    public DMLoadingTitleScreen() {
       super(false);
    }

    @SubscribeEvent
    public static void loadDMTitleScreen(final ScreenEvent.Init.Post event) {
            if (event.getScreen() instanceof TitleScreen && !(event.getScreen() instanceof DMLoadingTitleScreen) && DMConfig.Client.customtitlescreen.get()) {
                Minecraft.getInstance().setScreen(new DMLoadingTitleScreen());
        }

        if(event.getScreen() instanceof DMLoadingTitleScreen) {
        	DocMod.prepareDownload();
        	if(LaunchUtils.isRunningInDev()) {
        		if(DocTeamAPI.isDevLoginEnabled) {
        			if(!DocTeamAPI.isConnected()) {
        				event.getScreen().getMinecraft().setScreen(new DMLoginScreen());
        			}
        		} else {
            		final Window window = Minecraft.getInstance().getWindow();
                    window.setTitle("DocMod " + DMUtils.VERSION);
                    /*
                    Button docMod_server = new Button(325, 156, 100, 20, Component.translatable("button." + DocMod.MOD_ID + ".server_button"), (p_96781_) -> {
                        ConnectScreen.startConnecting(event.getScreen(), event.getScreen().getMinecraft(), new ServerAddress(Servers.HOST, Servers.PORT), new ServerData("DocMod Server", Servers.HOST, false));
                    });
                    Button discord_button = new Button(325, 133, 100, 20, Component.translatable("button." + DocMod.MOD_ID + ".discord_button"), (p_96781_) -> {
                        DMUtils.openLink("https://discord.gg/7VA9X67xRB");
                    });
                    Button screen_button = new Button(325, 110, 100, 20, Component.translatable("button." + DocMod.MOD_ID + ".screen_button"), (p_96781_) -> {
                        event.getScreen().getMinecraft().setScreen(new DocModServersSelection(event.getScreen()));
                    });

                     */
                    event.getScreen().getMinecraft().setScreen(new DMTitleScreen(true)); //Bring us to the custom main title
                    //event.getScreen().addRenderableWidget(docMod_server);
                    //event.getScreen().addRenderableWidget(discord_button);
                    //event.getScreen().addRenderableWidget(screen_button);
            	}
        	}
        	else {
        		final Window window = Minecraft.getInstance().getWindow();
                window.setTitle("DocMod " + DMUtils.VERSION);
                /*
                Button docMod_server = new Button(325, 156, 100, 20, Component.translatable("button." + DocMod.MOD_ID + ".server_button"), (p_96781_) -> {
                    ConnectScreen.startConnecting(event.getScreen(), event.getScreen().getMinecraft(), new ServerAddress(Servers.HOST, Servers.PORT), new ServerData("DocMod Server", Servers.HOST, false));
                });
                Button discord_button = new Button(325, 133, 100, 20, Component.translatable("button." + DocMod.MOD_ID + ".discord_button"), (p_96781_) -> {
                    DMUtils.openLink("https://discord.gg/7VA9X67xRB");
                });
                Button screen_button = new Button(325, 110, 100, 20, Component.translatable("button." + DocMod.MOD_ID + ".screen_button"), (p_96781_) -> {
                    event.getScreen().getMinecraft().setScreen(new DocModServersSelection(event.getScreen()));
                });

                 */
                event.getScreen().getMinecraft().setScreen(new DMTitleScreen(true)); //Bring us to the custom main title
                //event.getScreen().addRenderableWidget(docMod_server);
                //event.getScreen().addRenderableWidget(discord_button);
                //event.getScreen().addRenderableWidget(screen_button);
        	}
        }
       }
}
