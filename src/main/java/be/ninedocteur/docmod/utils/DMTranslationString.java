package be.ninedocteur.docmod.utils;

import net.minecraft.network.chat.Component;

public class DMTranslationString {
    public static Component TEST = translation("docmod.test");
    public static Component DM_COMMUNITY_SERVER = translation("docmod.communautyserver");

    private static Component translation(String translationKey){
        return Component.translatable(translationKey);
    }
}
