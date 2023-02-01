package fr.ninedocteur.docmod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;

public class DMOreBlock extends ExperienceDroppingBlock {

    public DMOreBlock(boolean isDeepSlate, float hardness, float resistance) {
        super(createSettings(isDeepSlate, hardness, resistance));
    }

    public static Settings createSettings(boolean isDeepSlate, float hardness, float resistance){
        Settings settings;
        if (isDeepSlate){
            settings = Settings.of(Material.STONE, DyeColor.BLACK).strength(hardness * 1.5f, resistance * 1.5f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE);
        }else {
            settings = Settings.of(Material.STONE, DyeColor.GRAY).strength(hardness, resistance).requiresTool().sounds(BlockSoundGroup.STONE);
        }
        return settings;
    }

    public DMOreBlock(DyeColor color, float hardness, float resistance) {
        super(createSettings(color, hardness, resistance));
    }

    public static Settings createSettings(DyeColor color, float hardness, float resistance){
        if (color == DyeColor.RED){
            return Settings.of(Material.STONE, color).strength(hardness, resistance).requiresTool().sounds(BlockSoundGroup.NETHER_ORE);
        }
        else return null;
    }
}
