package be.ninedocteur.docmod.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.serialization.JsonOps;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

import javax.imageio.ImageIO;
import javax.xml.transform.Result;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IOUtils {

    private static final Gson GSON = new GsonBuilder().create();

    private static final Map<String, ResourceLocation> DOWNLOADED_TEXTURES = new HashMap<>();
    public static final String ERROR_WHEN_CONNECTING = ChatFormatting.RED + "No Internet Connection.";

    private static String CAPE_USERS = readURLContent("https://api.docteam.tk/docmod/cape/");

    public static String readURLContent(String stringurl) {
        try {
            URL url = new URL(stringurl);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String string;
            while ((string = bufferedReader.readLine()) != null) stringBuffer.append(string);
            bufferedReader.close();
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return ChatFormatting.RED + "Cannot get information";
        }
    }

    /**
     * WIP
     */
    public static String noInternet(String setPingURLHere) {
        try {
            URL url = new URL(setPingURLHere);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String string;
            while ((string = bufferedReader.readLine()) != null)
                stringBuffer.append(string);
            bufferedReader.close();
            return stringBuffer.toString();
        } catch (Exception e) {
            return ERROR_WHEN_CONNECTING;
        }
    }

    public static ResourceLocation readTexture(final String url, String name){
        if(DOWNLOADED_TEXTURES.containsKey(url)){
            return DOWNLOADED_TEXTURES.get(url);
        }
        ResourceLocation resourceLocation = new ResourceLocation("docmod", "textures/" + name.toLowerCase());
        DOWNLOADED_TEXTURES.put(url, resourceLocation);
        try{
            InputStream inputStream = new URL(url).openStream();
            NativeImage image = NativeImage.read(inputStream);
            DynamicTexture texture = new DynamicTexture(image);
            inputStream.close();
            Minecraft.getInstance().getTextureManager().register(resourceLocation, texture);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resourceLocation;
    }

    public static ResourceLocation getServerIcon(final String url, String name){
        if(DOWNLOADED_TEXTURES.containsKey(url)){
            return DOWNLOADED_TEXTURES.get(url);
        }
        ResourceLocation resourceLocation = new ResourceLocation("docmod", "textures/" + name.toLowerCase());
        DOWNLOADED_TEXTURES.put(url, resourceLocation);
        try{
            InputStream inputStream = new URL(url).openStream();
            NativeImage image = NativeImage.read(inputStream);
            DynamicTexture texture = new DynamicTexture(image);
            inputStream.close();
            Minecraft.getInstance().getTextureManager().register(resourceLocation, texture);
        }catch (MalformedURLException e) {
            readTexture("http://docteam.capmine.tk/docteamdatabase/server/Community/Icon/default.png", "DefaultServerIcon");
        } catch (IOException e) {
            readTexture("http://docteam.capmine.tk/docteamdatabase/server/Community/Icon/default.png", "DefaultServerIcon");
        }
        return resourceLocation;
    }

    public static String getCapeUsers() {
        return CAPE_USERS;
    }

    @OnlyIn(Dist.CLIENT)
    public static ResourceLocation readImage(final String url){
        if(DOWNLOADED_TEXTURES.containsKey(url)){
            return DOWNLOADED_TEXTURES.get(url);
        }
        ResourceLocation resourceLocation = new ResourceLocation("docmod", "textures/");
        DOWNLOADED_TEXTURES.put(url, resourceLocation);
        try{
            InputStream inputStream = new URL(url).openStream();
            NativeImage image = NativeImage.read(inputStream);
            DynamicTexture texture = new DynamicTexture(image);
            inputStream.close();
            Minecraft.getInstance().getTextureManager().register(resourceLocation, texture);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resourceLocation;
    }

    public static void writeContentToFile(Object object, String filePath, Gson gson) {
        File f = new File(filePath.substring(0, filePath.lastIndexOf("/")));
        if (!f.exists())
            f.mkdirs();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(gson.toJson(object));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object loadContentFromFile(String filePath, Class c, Gson gson, boolean create) {
        File f = new File(filePath);
        if (!f.exists() && create)
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (f.exists())
            try {
                BufferedReader reader = new BufferedReader(new FileReader(f));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null)
                    sb.append(line);
                reader.close();
                Object o = gson.fromJson(sb.toString(), c);
                return o;
            } catch (Exception exception) {}
        return null;
    }


    public static Object loadJsonFile(){
        Gson gson = new Gson();
        BufferedReader br = null;

        try{
            URL url = new URL(IOUtils.readURLContent(""));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (Exception exception) {

        }

        return null;
    }

    public static Object loadJsonDatabase(){
        try{
        URL url = new URL(IOUtils.readURLContent("http://docteamwebsite.tk/modinfoio/database.json"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String string;
            while ((string = bufferedReader.readLine()) != null) stringBuffer.append(string);
            bufferedReader.close();
            return stringBuffer.toString();

        } catch (Exception exception) {

        }
        return null;
    }

    public static FluidStack readFluid(JsonObject json) {
        return FluidStack.CODEC.decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
    }

    public static JsonElement toJson(FluidStack stack) {
        return FluidStack.CODEC.encodeStart(JsonOps.INSTANCE, stack).result().orElseThrow();
    }
}
