package be.ninedocteur.docmod.common.computer;

import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;

public class Brain {
    public static Component NOT_EXIST = Component.literal("This command does not exist.");

    public static List<FormattedCharSequence> warnMessages = new ArrayList<>();
}
