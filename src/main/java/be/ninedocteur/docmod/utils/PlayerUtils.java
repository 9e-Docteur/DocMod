package be.ninedocteur.docmod.utils;

import net.minecraft.resources.ResourceLocation;

import java.time.LocalDate;
import java.util.UUID;

public class PlayerUtils {

    public static ResourceLocation getPlayerHead(final String name){
        return IOUtils.readTexture("https://minotar.net/helm/" + name + "/16.png", name + "_player_helm");
    }

    public static ResourceLocation get3DPlayerHead(final String uuid){
        return IOUtils.readTexture("https://crafatar.com/renders/head/" + uuid + "?scale=10&size=512&overlay", uuid + "_player_head");
    }
    
    public static ResourceLocation get3DPlayerHead(final UUID uuid){
        return IOUtils.readTexture("https://crafatar.com/renders/head/" + uuid + "?scale=10&size=512&overlay", uuid + "_player_head");
    }

    public static ResourceLocation get3DSkin(final String uuid){
        return IOUtils.readTexture("https://crafatar.com/renders/body/" + uuid, uuid + "_player_body");
    }

    public static ResourceLocation getSkin(final String UUID){
        return IOUtils.readTexture("https://crafatar.com/skins/", UUID);
    }
    
    public static UUID getUserUUID(String playerName) {
    	return UUID.fromString(IOUtils.readURLContent("https://minecraft-api.com/api/uuid/" + playerName).replaceFirst(
                "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"
        ));
    }
    
    public static ResourceLocation getFreeSlotTexture() {
    	LocalDate date = LocalDate.now();
    	if(date.getMonthValue() == 7 && date.getDayOfMonth() == 14){
    		return new ResourceLocation("docmod", "textures/gui/server/free_slot_belgium.png");
    	} else if(date.getMonthValue() == 12){
    		return new ResourceLocation("docmod", "textures/gui/server/free_slot_christmas.png");
    	} else {
    		return new ResourceLocation("docmod", "textures/gui/server/free_slot.png");
    	}
    }
    
    public static String getUserUUIDAsString(String playerName) {
    	return IOUtils.readURLContent("https://minecraft-api.com/api/uuid/" + playerName);
    }

    public static String getUserNameByUUID(UUID playerUUID) {
        return IOUtils.readURLContent("https://minecraft-api.com/api/pseudo/" + playerUUID);
    }
}
