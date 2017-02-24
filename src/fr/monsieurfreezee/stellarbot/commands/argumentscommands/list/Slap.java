package fr.monsieurfreezee.stellarbot.commands.argumentscommands.list;

import fr.monsieurfreezee.stellarbot.commands.CommandDetails;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.Argument;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentCommand;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentType;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentsList;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;

@CommandDetails(name = "Slap", commandName = "Slap", description = "Pour fouetter les gens :3")
@ArgumentsList(arguments = {ArgumentType.USER, ArgumentType.INTEGER, ArgumentType.FLOAT})
public class Slap implements ArgumentCommand {

    @Override
    public String run(GenericMessageEvent event, Argument... arguments) {
        User toSlap = arguments[0].getUser();
        int times = arguments[1].getInteger();
        float ruleSize = arguments[2].getFloat();
        return event.getAuthor().getName() + " slapped " + toSlap.getAsMention() + " " + times + " times in a row with a " + ruleSize + "cm rule";
    }
}
