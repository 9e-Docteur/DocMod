package be.ninedocteur.docmod.common.computer.terminal.command.shop;

import be.ninedocteur.docmod.common.computer.terminal.command.BaseCommand;

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
