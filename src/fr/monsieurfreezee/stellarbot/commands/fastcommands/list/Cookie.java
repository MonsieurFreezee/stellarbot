package fr.monsieurfreezee.stellarbot.commands.fastcommands.list;

import fr.monsieurfreezee.stellarbot.commands.CommandDetails;
import fr.monsieurfreezee.stellarbot.commands.fastcommands.FastCommand;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;

@CommandDetails(name = "ROMNOM", commandName = "Cookie", description = "I like cookies.")
public class Cookie implements FastCommand {

    @Override
    public String run(GenericMessageEvent event) {
        return ":cookie:";
    }
}
