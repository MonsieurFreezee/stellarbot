package fr.monsieurfreezee.stellarbot.commands.fastcommands;

import fr.monsieurfreezee.stellarbot.commands.Command;
import fr.monsieurfreezee.stellarbot.commands.CommandException;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;

public interface FastCommand extends Command {

    String run(GenericMessageEvent event) throws CommandException;
}
