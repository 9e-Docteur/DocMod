package fr.ninedocteur.docmod.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IOUtils {
    private static final Gson GSON = new GsonBuilder().create();

    private static final Map<String, Identifier> DOWNLOADED_TEXTURES = new HashMap<>();
    public static final String ERROR_WHEN_CONNECTING = Formatting.RED + "No Internet Connection.";

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
            return Formatting.RED + "Cannot get information";
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

    public static Identifier readTexture(final String url, String name){
        if(DOWNLOADED_TEXTURES.containsKey(url)){
            return DOWNLOADED_TEXTURES.get(url);
        }
        Identifier resourceLocation = new Identifier("docmod", "textures/" + name.toLowerCase());
        DOWNLOADED_TEXTURES.put(url, resourceLocation);
        try{
            InputStream inputStream = new URL(url).openStream();
            NativeImage image = NativeImage.read(inputStream);
            NativeImageBackedTexture texture = new NativeImageBackedTexture(image);
            inputStream.close();
            MinecraftClient.getInstance().getTextureManager().registerTexture(resourceLocation, texture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resourceLocation;
    }

    public static Identifier getServerIcon(final String url, String name){
        if(DOWNLOADED_TEXTURES.containsKey(url)){
            return DOWNLOADED_TEXTURES.get(url);
        }
        Identifier resourceLocation = new Identifier("docmod", "textures/" + name.toLowerCase());
        DOWNLOADED_TEXTURES.put(url, resourceLocation);
        try{
            InputStream inputStream = new URL(url).openStream();
            NativeImage image = NativeImage.read(inputStream);
            NativeImageBackedTexture texture = new NativeImageBackedTexture(image);
            inputStream.close();
            MinecraftClient.getInstance().getTextureManager().registerTexture(resourceLocation, texture);
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

    public static Identifier readImage(final String url){
        if(DOWNLOADED_TEXTURES.containsKey(url)){
            return DOWNLOADED_TEXTURES.get(url);
        }
        Identifier resourceLocation = new Identifier("docmod", "textures/");
        DOWNLOADED_TEXTURES.put(url, resourceLocation);
        try{
            InputStream inputStream = new URL(url).openStream();
            NativeImage image = NativeImage.read(inputStream);
            NativeImageBackedTexture texture = new NativeImageBackedTexture(image);
            inputStream.close();
            MinecraftClient.getInstance().getTextureManager().registerTexture(resourceLocation, texture);
        }catch (MalformedURLException e) {
            readTexture("http://130.61.36.120/docteam/default.png", "def");
        } catch (IOException e) {
            readTexture("http://130.61.36.120/docteam/default.png", "def");
        }
        return resourceLocation;
    }

    public static Identifier readServerIcon(final String url, String serverName){
        if(DOWNLOADED_TEXTURES.containsKey(url)){
            return DOWNLOADED_TEXTURES.get(url);
        }
        String regex = "[^a-z0-9/._-]";
        String serverFilterName = serverName.toLowerCase().replaceAll(regex, "");
        Identifier resourceLocation = new Identifier("docmod", "textures/" + serverFilterName);
        DOWNLOADED_TEXTURES.put(url, resourceLocation);
        try{
            InputStream inputStream = new URL(url).openStream();
            NativeImage image = NativeImage.read(inputStream);
            NativeImageBackedTexture texture = new NativeImageBackedTexture(image);
            inputStream.close();
            MinecraftClient.getInstance().getTextureManager().registerTexture(resourceLocation, texture);
        } catch (IOException | InvalidIdentifierException e) {
            readTexture("http://130.61.36.120/docteam/default.png", "def");
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
