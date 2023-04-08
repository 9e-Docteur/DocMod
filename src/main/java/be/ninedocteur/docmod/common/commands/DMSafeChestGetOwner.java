package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.client.gui.menu.SafeChestMenu;
import be.ninedocteur.docmod.common.tileentity.SafeChestTileEntity;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class DMSafeChestGetOwner {
    public DMSafeChestGetOwner(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("safechest").then(Commands.literal("getOwner").then(Commands.argument("pos", BlockPosArgument.blockPos()).executes((command) -> {
            return execute(command.getSource(), BlockPosArgument.getLoadedBlockPos(command, "pos"));
        })))));
    }

    private int execute(CommandSourceStack source, BlockPos pos) throws CommandSyntaxException {

//        Level level = Minecraft.getInstance().level;
//        SafeChestTileEntity safeChestTileEntity = (SafeChestTileEntity) level.getBlockEntity(pos);
//        if(safeChestTileEntity == null){
//            source.sendSystemMessage(Component.literal(ChatFormatting.RED + "This is not a Safe Chest! Can't get information!"));
//        } else {
//            source.sendSystemMessage(Component.literal("This Safe Chest belong to " + safeChestTileEntity.getOwner()));
//
//        }
        return 1;
    }
}
