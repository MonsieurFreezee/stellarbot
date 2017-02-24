package fr.monsieurfreezee.stellarbot.commands.fastcommands.list;

import fr.monsieurfreezee.stellarbot.commands.CommandDetails;
import fr.monsieurfreezee.stellarbot.commands.CommandException;
import fr.monsieurfreezee.stellarbot.commands.fastcommands.FastCommand;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;

@CommandDetails(name = "Monsieur ?", commandName = "Ping", description = "Si jamais vous êtes pas trop sûr que le bot soit en vie")
public class Ping implements FastCommand {

    @Override
    public String run(GenericMessageEvent event) throws CommandException {
        return "Pong!";
    }
}
