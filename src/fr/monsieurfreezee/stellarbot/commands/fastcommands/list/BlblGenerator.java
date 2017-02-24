package fr.monsieurfreezee.stellarbot.commands.fastcommands.list;

import fr.monsieurfreezee.stellarbot.commands.CommandDetails;
import fr.monsieurfreezee.stellarbot.commands.fastcommands.FastCommand;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;

import java.util.Random;

@CommandDetails(name = "Blbl", commandName = "Blbl", aliases = {"Bl"}, description = "ARBLRLBRLBLBLRLLRBLLLBLBLb...l")
public class BlblGenerator implements FastCommand {

    @Override
    public String run(GenericMessageEvent event) {
        String bl = "b";
        boolean lastIsB = true;

        for (int i = 0 ; i < 10 ; i++) {
            if (new Random().nextBoolean() && !lastIsB) {
                bl += "b";
                lastIsB = true;
            } else {
                bl += "l";
                lastIsB = false;
            }
        }

        return bl + " !";
    }
}
