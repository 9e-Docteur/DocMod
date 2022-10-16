package be.ninedocteur.docmod.common.computer.command.shop;

import be.ninedocteur.docmod.common.computer.BaseOS;
import be.ninedocteur.docmod.common.computer.OSRegistry;
import be.ninedocteur.docmod.common.computer.command.BaseCommand;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class StaffCommand extends BaseCommand {

    public StaffCommand() {
        super("staff", "", "Show staff member of DocTeam", "staff", CommandType.SHOP);
    }

    @Override
    public void performCommand(String[] args) {
        answer("9e_Docteur - CEO, Dev", AnswerType.NORMAL);
        answer("Killar.exe - Dev", AnswerType.NORMAL);
        answer("PandaRebel - Assets Manager", AnswerType.NORMAL);
        answer("Josia - Dev", AnswerType.NORMAL);
    }
}
