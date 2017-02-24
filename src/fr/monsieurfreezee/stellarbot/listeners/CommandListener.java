package fr.monsieurfreezee.stellarbot.listeners;

import fr.monsieurfreezee.stellarbot.commands.Command;
import fr.monsieurfreezee.stellarbot.commands.CommandDetails;
import fr.monsieurfreezee.stellarbot.commands.CommandException;
import fr.monsieurfreezee.stellarbot.commands.CommandsRegisterer;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.Argument;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentCommand;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentType;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentsList;
import fr.monsieurfreezee.stellarbot.commands.fastcommands.FastCommand;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import java.util.ArrayList;
import java.util.List;

public class CommandListener implements EventListener {

    private void processArgumentCommand(GenericMessageEvent e, String message, ArgumentCommand argsCommand) throws CommandException {
        CommandDetails commandDetails = getCommandDetails(argsCommand);
        ArgumentsList argumentsList = getArgumentsList(argsCommand);

        assert commandDetails != null;
        assert argumentsList != null;
        String messageWithoutArgs = message.substring(1 + commandDetails.commandName().toLowerCase().length());
        Argument argumentsToSend[] = new Argument[argumentsList.arguments().length];

        for (int i = 0; i < argumentsList.arguments().length; i++) {
            messageWithoutArgs = messageWithoutArgs.trim();
            String argument = null;

            if (argumentsList.arguments()[i] == ArgumentType.TEXT && i != argumentsList.arguments().length - 1) {
                throw new CommandException("Text must be only at the end");
            }

            if (argumentsList.arguments()[i] == ArgumentType.USER) {
                List<User> mentionedUsers = e.getMessage().getMentionedUsers();

                for (User user: mentionedUsers) {
                    if (e instanceof GuildMessageReceivedEvent) {
                        GuildMessageReceivedEvent guildEvent = (GuildMessageReceivedEvent) e;
                        argument = "@" + guildEvent.getGuild().getMember(user).getEffectiveName();
                    } else {
                        argument = "@" + user.getName();
                    }

                    if (messageWithoutArgs.startsWith(argument)) {
                        argumentsToSend[i] = new Argument(user);
                        break;
                    }
                }
            } else if (argumentsList.arguments()[i] == ArgumentType.TEXT) {
                argument = messageWithoutArgs;
                argumentsToSend[i] = new Argument(argument);
            } else if (argumentsList.arguments()[i] == ArgumentType.TEXTCHANNEL) {
                if (e instanceof GuildMessageReceivedEvent) {
                    GuildMessageReceivedEvent guildEvent = (GuildMessageReceivedEvent) e;
                    TextChannel textChannel;
                    if (messageWithoutArgs.startsWith("#")) {
                        textChannel = guildEvent.getGuild().getTextChannelsByName(messageWithoutArgs.split(" ")[0].substring(1), true).get(0);
                        argument = "#";
                    }
                    else {
                        textChannel = guildEvent.getGuild().getTextChannelsByName(messageWithoutArgs.split(" ")[0], true).get(0);
                        argument = "";
                    }

                    if (textChannel != null) {
                        argumentsToSend[i] = new Argument(textChannel);
                        argument += textChannel.getName();
                    } else {
                        throw new CommandException("Text channel cannot be found");
                    }
                }
            } else if (argumentsList.arguments()[i] == ArgumentType.INTEGER) {
                String integer = messageWithoutArgs.split(" ")[0];
                try {
                    int result = Integer.parseInt(integer);
                    argument = integer;
                    argumentsToSend[i] = new Argument(result);
                } catch (NumberFormatException e1) {
                   throw new CommandException("Tried to parse incorrect integer number");
                }
            } else if (argumentsList.arguments()[i] == ArgumentType.FLOAT) {
                String floatNumber = messageWithoutArgs.split(" ")[0];
                try {
                    float result = Float.parseFloat(floatNumber);
                    argument = floatNumber;
                    argumentsToSend[i] = new Argument(result);
                } catch (NumberFormatException e1) {
                    throw new CommandException("Tried to parse incorrect float number");
                }
            }

            if (argumentsToSend[i] == null) {
                throw new CommandException("Did not send the argument n." + i);
            } else if (argument != null) {
                messageWithoutArgs = messageWithoutArgs.substring(argument.length());
            } else {
                throw new CommandException("Cannot build the argument n." + i);
            }
        }

        String result = argsCommand.run(e, argumentsToSend);

        if (e instanceof GuildMessageReceivedEvent) {
            GuildMessageReceivedEvent guildEvent = (GuildMessageReceivedEvent) e;
            sendMessageWithThreadDelete(splitMessages(result, e), guildEvent.getChannel());
        } else if (e instanceof PrivateMessageReceivedEvent) {
            PrivateMessageReceivedEvent privateEvent = (PrivateMessageReceivedEvent) e;
            sendMessageWithThreadDelete(splitMessages(result, e), privateEvent.getChannel());
        }
    }

    private void processFastCommand(GenericMessageEvent e, FastCommand fastCommand) throws CommandException {
        String result = fastCommand.run(e);

        if (e instanceof GuildMessageReceivedEvent) {
            GuildMessageReceivedEvent guildEvent = (GuildMessageReceivedEvent) e;
            sendMessageWithThreadDelete(splitMessages(result, e), guildEvent.getChannel());
        } else if (e instanceof PrivateMessageReceivedEvent) {
            PrivateMessageReceivedEvent privateEvent = (PrivateMessageReceivedEvent) e;
            sendMessageWithThreadDelete(splitMessages(result, e), privateEvent.getChannel());
        }
    }

    private ArrayList<String> splitMessages(String result, GenericMessageEvent e) {
        result = result.trim();
        String save = result;
        result = e.getAuthor().getAsMention() + " " + save;

        ArrayList<String> messages =  new ArrayList<>();
        if (result.length() > 2000) {

            while (result.length() > 2000) {
                int index = result.lastIndexOf("\n", 2000);

                if (index == -1)
                    index = result.lastIndexOf(".", 2000);
                if (index == -1)
                    index = result.lastIndexOf(" ", 2000);
                if (index == -1)
                    index = 2000;

                String temp = result.substring(0, index).trim();
                if (!temp.equals(""))
                    messages.add(temp);

                result = result.substring(index).trim();
            }
            if (!result.equals(""))
                messages.add(result);

            if (e instanceof GuildMessageReceivedEvent) {
                GuildMessageReceivedEvent guildEvent = (GuildMessageReceivedEvent) e;

                sendMessageWithThreadDelete(messages, guildEvent.getChannel());
            } else if (e instanceof PrivateMessageReceivedEvent) {
                PrivateMessageReceivedEvent privateEvent = (PrivateMessageReceivedEvent) e;

                sendMessageWithThreadDelete(messages, privateEvent.getChannel());
            }
        } else if (result.length() >= 1) {
            messages.add(result);
        }

        return messages;
    }

    private void sendMessageWithThreadDelete(ArrayList<String> messages, MessageChannel channel) {
        messages.forEach(s -> channel.sendMessage(s).queue(m -> {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(30000);
                    m.deleteMessage();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            });

            thread.start();
        }));
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof GenericMessageEvent) {
            GenericMessageEvent e = (GenericMessageEvent) event;

            String message = e.getMessage().getContent() + " ";

            if (message.startsWith("/")) {
                for (ArgumentCommand argsCommand: CommandsRegisterer.getArgumentCommands()) {
                    CommandDetails commandDetails = getCommandDetails(argsCommand);
                    ArgumentsList argumentsList = getArgumentsList(argsCommand);

                    if (commandDetails != null && argumentsList != null) {
                        try {
                            if ((message.trim() + " ").toLowerCase().startsWith("/" + commandDetails.commandName().toLowerCase() + " ")) {
                                processArgumentCommand(e, message, argsCommand);
                                return;
                            } else {
                                for (String alias : commandDetails.aliases()) {
                                    if (message.trim().toLowerCase().startsWith("/" + alias.toLowerCase() + " ")) {
                                        processArgumentCommand(e, message, argsCommand);
                                        return;
                                    }
                                }
                            }
                        } catch (CommandException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            } else if (message.contains("!")) {
                for (FastCommand fastCommand: CommandsRegisterer.getFastCommands()) {
                    CommandDetails commandDetails = getCommandDetails(fastCommand);

                    if (commandDetails != null) {
                        try {
                            if (message.toLowerCase().contains("!" + commandDetails.commandName().toLowerCase() + " ")) {
                                processFastCommand(e, fastCommand);
                                return;
                            } else {
                                for (String alias : commandDetails.aliases()) {
                                    if (message.toLowerCase().contains("!" + alias.toLowerCase() + " ")) {
                                        processFastCommand(e, fastCommand);
                                        return;
                                    }
                                }
                            }
                        } catch (CommandException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static CommandDetails getCommandDetails(Command command) {
        if (command.getClass().isAnnotationPresent(CommandDetails.class)) {
            return command.getClass().getAnnotation(CommandDetails.class);
        } else {
            return null;
        }
    }

    private ArgumentsList getArgumentsList(ArgumentCommand argumentCommand) {
        if (argumentCommand.getClass().isAnnotationPresent(ArgumentsList.class)) {
            return argumentCommand.getClass().getAnnotation(ArgumentsList.class);
        } else {
            return null;
        }
    }
}
