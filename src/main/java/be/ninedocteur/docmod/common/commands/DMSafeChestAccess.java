package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.client.gui.menu.SafeChestMenu;
import be.ninedocteur.docmod.common.tileentity.SafeChestTileEntity;
import be.ninedocteur.docmod.utils.TeamUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class DMSafeChestAccess {
    public DMSafeChestAccess(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("safechest").then(Commands.literal("access").then(Commands.argument("pos", BlockPosArgument.blockPos()).executes((command) -> {
            return execute(command.getSource(), BlockPosArgument.getLoadedBlockPos(command, "pos"));
        })))));
    }

    private int execute(CommandSourceStack source, BlockPos pos) throws CommandSyntaxException {

        Level level = Minecraft.getInstance().level;
        SafeChestTileEntity safeChestTileEntity = (SafeChestTileEntity) level.getBlockEntity(pos);
        if(safeChestTileEntity == null){
            source.sendSystemMessage(Component.literal(ChatFormatting.RED + "This is not a Safe Chest! Can't access!"));
        } else {
            Player player = source.getPlayer();
            final MenuProvider container = new SimpleMenuProvider(SafeChestMenu.getServerContainer(safeChestTileEntity, pos), safeChestTileEntity.getDefaultName());
            NetworkHooks.openScreen((ServerPlayer) player, container, pos);
        }
        return 1;
    }
}
