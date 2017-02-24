package fr.monsieurfreezee.stellarbot.commands.fastcommands.list;

import fr.monsieurfreezee.stellarbot.commands.CommandDetails;
import fr.monsieurfreezee.stellarbot.commands.fastcommands.FastCommand;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;

@CommandDetails(name = "Mick aime les cookies", commandName = "Mick", aliases = {"MickLeCookisateur"}, description = "In cookies we trust o/")
public class MickLeCookisateur implements FastCommand {

    @Override
    public String run(GenericMessageEvent event) {
        return "(°<°) :cookie:";
    }
}
