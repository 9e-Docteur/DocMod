package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.common.tileentity.EnderPadTile;
import be.ninedocteur.docmod.utils.DMUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class DMEnderTileSetName {

    public DMEnderTileSetName(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("enderpad").then(Commands.argument("pos", BlockPosArgument.blockPos()).then(Commands.literal("setname").then(Commands.argument("name", StringArgumentType.greedyString()).executes((command) -> {
            return execute(command.getSource(), BlockPosArgument.getBlockPos(command, "pos"), StringArgumentType.getString(command, "name"));
        }))))));
    }

    private int execute(CommandSourceStack source, BlockPos tilePos, String name) throws CommandSyntaxException {
    	EnderPadTile tile = (EnderPadTile) source.getLevel().getBlockEntity(tilePos);
    	tile.setName(name);
    	tile.hasName = true;
        source.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Name of the tile updated!"));
        return 1;
    }

}
