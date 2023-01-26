package be.ninedocteur.docmod.client.gui.screens;

import java.io.IOException;
import java.nio.file.Files;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;

import be.ninedocteur.docmod.client.gui.title.DMTitleScreen;
import be.ninedocteur.docmod.utils.ColorUtils;
import be.ninedocteur.docteam.api.DocTeamAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;

public class DMLoginScreen extends Screen{
	public EditBox usernameField, passwordField;
	public Button login, close;
	private boolean isWrongLogin;

	public DMLoginScreen() {
		super(Component.empty());
		isWrongLogin = false;
	}
	
	@Override
	public void renderDirtBackground(int p_96627_) {
		// TODO Auto-generated method stub
		super.renderDirtBackground(p_96627_);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();
		
		if(DocTeamAPI.userFile().exists()) {
			try {
				String username = Files.readString(DocTeamAPI.userFile().toPath()).split("\n")[0];
				String password = Files.readString(DocTeamAPI.userFile().toPath()).split("\n")[1];
				DocTeamAPI api = new DocTeamAPI(username, password);
				Minecraft.getInstance().setScreen(new DMTitleScreen());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		usernameField = new EditBox(this.font, this.width / 2 - 80, this.height / 2 - 10, 170, 20, Component.literal("username"));
		passwordField = new EditBox(this.font, this.width / 2 - 80, this.height / 2 + 30, 170, 20, Component.literal("password"));
		login = Button.builder(Component.translatable("Login"), (p_96781_) -> {
           //TODO: LOGIN
			DocTeamAPI api = new DocTeamAPI(usernameField.getValue(), passwordField.getValue());
			if(DocTeamAPI.isConnected()) {
				if(!DocTeamAPI.userFile().exists()) {
					try {
						DocTeamAPI.userFile().createNewFile();
						Files.writeString(DocTeamAPI.userFile().toPath(), usernameField.getValue() + "\n" + passwordField.getValue());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Minecraft.getInstance().setScreen(new DMTitleScreen());
			} else {
				isWrongLogin = true;
			}
        }).bounds(this.width / 2 - 82, this.height / 2 + 60, 85, 20).build();
		close = Button.builder(Component.translatable("Cancel"), (p_96781_) -> {
	      Minecraft.getInstance().setScreen(new DMTitleScreen());
	    }).bounds(this.width / 2 + 8, this.height / 2 + 60, 85, 20).build();
		addRenderableWidget(usernameField);
		addRenderableWidget(passwordField);
		addRenderableWidget(login);
		addRenderableWidget(close);
	
		
	}
	
	@Override
	public void render(PoseStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
		// TODO Auto-generated method stub
		renderDirtBackground(p_96564_);
		drawCenteredString(p_96562_, font, "Username:", usernameField.getX() + 25, usernameField.getY() - 12, -1);
		drawCenteredString(p_96562_, font, "Password:", passwordField.getX() + 25, passwordField.getY() - 12, -1);
		drawCenteredString(p_96562_, font, "Welcome to DocMod Dev. Please log in to have whole access of the dev features.", usernameField.getX() + 80, usernameField.getY() - 50, -1);
		if(isWrongLogin) {
			drawCenteredString(p_96562_, font, "Wrong login!", usernameField.getX() + 80, usernameField.getY() - 40, ColorUtils.getRed());
		}
		super.render(p_96562_, p_96563_, p_96564_, p_96565_);
	}

}
