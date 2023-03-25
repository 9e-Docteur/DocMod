package be.ninedocteur.docmod.registry;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.utils.PlayerUtils;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModelRegistry {

    public static final ModelLayerLocation MODEl = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "model"), "model");
    public static final ModelLayerLocation Glass = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "glass"), "glass");
    public static final ModelLayerLocation Tardis = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "tardis"), "tardis");
    public static final ModelLayerLocation ZurbTeleporter = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "zurbteleporter"), "tardis");
    public static final ModelLayerLocation RedGlass = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "glass"), "glass");
    public static final ModelLayerLocation OrangeGlass = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "glass"), "glass");
    public static final ModelLayerLocation GreenGlass = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "glass"), "glass");
    public static final ModelLayerLocation ITEM_SHOWER = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "is"), "is");
    public static final ModelLayerLocation PANDA_SKIN = new ModelLayerLocation(PlayerUtils.getSkin("45949380-580d-4dd3-8526-6ee05dd75c22"), "pandaskin");
    public static final ModelLayerLocation RED_TOYOTA = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "red"), "red");
    public static final ModelLayerLocation NEW_Tardis = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "textures/block/2018_police_box"), "textures/block/2018_police_box");
    public static final ModelLayerLocation DAMAGED_DALEK = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "damaged_dalek"), "damaged_dalek");
    public static final ModelLayerLocation COMPUTER = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "computer"), "computer");
    public static final ModelLayerLocation MONITOR = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "monitor"), "monitor");
    public static final ModelLayerLocation CHAIR = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "chair"), "chair");
    public static final ModelLayerLocation TARDIS_DOOR = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "door"), "door");

    public static final ModelLayerLocation SAFE_CHEST = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "safe"), "safe");

    //ENTITY
    public static final ModelLayerLocation Zurbion = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "zurbion"), "zurbion");
    public static final ModelLayerLocation PLAYER = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "player"), "player");
}
