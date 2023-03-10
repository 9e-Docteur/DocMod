package be.ninedocteur.docmod.common.item.sonic;

import be.ninedocteur.docmod.common.block.DMRedstoneLampOn;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.sound.DMSound;
import be.ninedocteur.docmod.sonic.SonicInteractionItem;
import be.ninedocteur.docmod.utils.KeyUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GaratimSonic extends SonicItem {
    public GaratimSonic(Properties pProperties) {
        super(pProperties);
        this.setupSonic(DMSound.GARATIM_SONIC.get(), true, true, true, true, true, true, true);
        this.addCredits(Component.literal(ChatFormatting.YELLOW  + "Made by: " + ChatFormatting.WHITE + "Garatim"));
    }
}
