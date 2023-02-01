package fr.ninedocteur.docmod.registry;

import fr.ninedocteur.docmod.DocMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModelRegistry {
    public static final EntityModelLayer Glass = new EntityModelLayer(new Identifier(DocMod.MOD_ID, "glass"), "glass");
}
