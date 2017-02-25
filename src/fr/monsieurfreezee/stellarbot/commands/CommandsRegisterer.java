package fr.monsieurfreezee.stellarbot.commands;

import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentCommand;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.list.Slap;
import fr.monsieurfreezee.stellarbot.commands.fastcommands.FastCommand;
import fr.monsieurfreezee.stellarbot.commands.fastcommands.list.*;

import java.util.ArrayList;

public class CommandsRegisterer {

    private static ArrayList<FastCommand> fastCommands = new ArrayList<>();
    private static ArrayList<ArgumentCommand> argumentCommands = new ArrayList<>();

    static {
        // Fast commands:
        fastCommands.add(new BlblGenerator());
        fastCommands.add(new Cookie());
        fastCommands.add(new Help());
        fastCommands.add(new MickLeCookisateur());
        fastCommands.add(new Online());
        fastCommands.add(new Ping());

        // Argument commands:
        argumentCommands.add(new Slap());
    }

    public static ArrayList<FastCommand> getFastCommands() {
        return fastCommands;
    }

    public static ArrayList<ArgumentCommand> getArgumentCommands() {
        return argumentCommands;
    }
}
