package be.ninedocteur.docmod.data.json;

import java.lang.reflect.Type;

import com.google.gson.*;

import be.ninedocteur.docmod.utils.IOUtils;
import net.minecraft.resources.ResourceLocation;

public class DMServer {
	private String name, owner, icon, ip;
	private int id, port;
	
	private DMServer(String name, String owner, String icon, String ip, int id, int port) {
		this.owner = owner;
		this.name = name;
		this.icon = icon;
		this.port = port;
		this.ip = ip;
		this.id = id;
	}
	
	public ResourceLocation getServerIcon() {
		return IOUtils.getServerIcon("http://docteam.capmine.tk/docteamdatabase/server/Community/Icon/" + icon, "server" + name + "Icon");
	}
	
	public String getName() {
		return name;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public String getIp() {
		return ip;
	}
	
	public int getPort() {
		return port;
	}
	
	public int getId() {
		return id;
	}
	
	public static class Deserializer implements JsonDeserializer<DMServer>{

		@Override
		public DMServer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
			JsonObject object = json.getAsJsonObject();
			String name = object.get("name").getAsString();
			String owner = object.get("owner").getAsString();
			String icon = object.get("icon").getAsString();
			String ip = object.get("serverIP").getAsString();
			int port = object.get("port").getAsInt();
			int id = object.get("id").getAsInt();
			return new DMServer(name, owner, icon, ip, id, port);
		}
		
	}
}
