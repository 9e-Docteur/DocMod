package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.common.item.sonic.NinedocteurSonic;
import be.ninedocteur.docmod.common.item.sonic.SonicItem;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class DMSonicChargeCommand {

    public DMSonicChargeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("sonic").then(Commands.literal("charge").then(Commands.argument("int", IntegerArgumentType.integer(1, 100)).executes((command) -> {
            return execute(command.getSource(), IntegerArgumentType.getInteger(command, "int"));
        })))));
    }

    private int execute(CommandSourceStack source, int charge) throws CommandSyntaxException {
        SonicItem sonic = (SonicItem) source.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem();
        if(source.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof SonicItem){
            sonic.setCharge(charge);
            source.sendSuccess(Component.literal(ChatFormatting.GREEN + "Sonic charge set to " + charge), true);
        } else {
            source.sendSuccess(Component.literal(ChatFormatting.RED + "You do not have a sonic in your main hand."), true);
        }
        return 1;
    }

}
