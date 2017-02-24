package fr.monsieurfreezee.stellarbot.commands.argumentscommands;

import fr.monsieurfreezee.stellarbot.commands.Command;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;

public interface ArgumentCommand extends Command {

    String run(GenericMessageEvent event, Argument... arguments);
}
