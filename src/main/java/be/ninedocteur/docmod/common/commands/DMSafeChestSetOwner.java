package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.common.tileentity.SafeChestTileEntity;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class DMSafeChestSetOwner {
    public DMSafeChestSetOwner(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("safechest").then(Commands.literal("setOwner").then(Commands.argument("pos", BlockPosArgument.blockPos()).then(Commands.argument("player", EntityArgument.player())).executes((command) -> {
            return execute(command.getSource(), BlockPosArgument.getLoadedBlockPos(command, "pos"), EntityArgument.getPlayer(command, "player"));
        })))));
    }

    private int execute(CommandSourceStack source, BlockPos pos, ServerPlayer serverPlayer) throws CommandSyntaxException {

//        Level level = Minecraft.getInstance().level;
//        SafeChestTileEntity safeChestTileEntity = (SafeChestTileEntity) level.getBlockEntity(pos);
//        if(safeChestTileEntity == null){
//            source.sendSystemMessage(Component.literal(ChatFormatting.RED + "This is not a Safe Chest! Can't get information!"));
//        } else {
//            safeChestTileEntity.setOwner(serverPlayer.getUUID());
//            source.sendSystemMessage(Component.literal("New owner of this Safe Chest " + safeChestTileEntity.getOwner()));
//
//        }
        return 1;
    }
}
