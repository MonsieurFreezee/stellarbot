package fr.monsieurfreezee.stellarbot.commands.fastcommands.list;

import fr.monsieurfreezee.stellarbot.commands.CommandDetails;
import fr.monsieurfreezee.stellarbot.commands.CommandsRegisterer;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentCommand;
import fr.monsieurfreezee.stellarbot.commands.fastcommands.FastCommand;
import fr.monsieurfreezee.stellarbot.listeners.CommandListener;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import org.apache.commons.lang3.StringUtils;

@CommandDetails(name = "Besoin d'aide ?", commandName = "Help", aliases = {"Aide"}, description = "Liste toutes les commandes")
public class Help implements FastCommand {

    @Override
    public String run(GenericMessageEvent e) {
        String result = "Commandes existantes : \n";

        for (FastCommand command : CommandsRegisterer.getFastCommands()) {
            CommandDetails commandDetails = CommandListener.getCommandDetails(command);

            result += commandDetails.name() + " : `" + "!" + commandDetails.commandName() + "`";
            if (commandDetails.aliases().length != 0) {
                result += " [alias :";
                for (String alias: commandDetails.aliases()) {
                    result += " `" + "!" + alias + "`,";
                }

                result = StringUtils.removeEnd(result, ",");
                result += "]";
            }

            result += ": " + commandDetails.description() + "\n";
        }

        for (ArgumentCommand command : CommandsRegisterer.getArgumentCommands()) {
            CommandDetails commandDetails = CommandListener.getCommandDetails(command);

            result += commandDetails.name() + " : `" + "/" + commandDetails.commandName() + "`";
            if (commandDetails.aliases().length != 0) {
                result += " [alias:";
                for (String alias: commandDetails.aliases()) {
                    result += " `" + "/" + alias + "`,";
                }

                result = StringUtils.removeEnd(result, ",");
                result += "]";
            }

            result += ": " + commandDetails.description() + "\n";
        }

        return result;
    }
}
