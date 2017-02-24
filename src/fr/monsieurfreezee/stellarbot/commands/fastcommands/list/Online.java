package fr.monsieurfreezee.stellarbot.commands.fastcommands.list;

import fr.monsieurfreezee.stellarbot.Bot;
import fr.monsieurfreezee.stellarbot.commands.CommandDetails;
import fr.monsieurfreezee.stellarbot.commands.CommandException;
import fr.monsieurfreezee.stellarbot.commands.fastcommands.FastCommand;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

@CommandDetails(name = "Qui est en ligne ?", commandName = "Online", aliases = {"EnLigne", "Connectés"}, description = "Affiche la liste des membres en ligne")
public class Online implements FastCommand {

    @Override
    public String run(GenericMessageEvent event) throws CommandException {
        if (event instanceof GuildMessageReceivedEvent) {
            GuildMessageReceivedEvent guildEvent = (GuildMessageReceivedEvent) event;
            String result = "";

            List<Member> onlineMember = new ArrayList<>();
            List<Member> afkMembers = new ArrayList<>();
            List<Member> doNotDisturbUsers = new ArrayList<>();
            List<Member> unknownMembers = new ArrayList<>();

            guildEvent.getGuild().getMembers().forEach(user -> {
                if (!(user.equals(Bot.jda.getSelfUser()))) {
                    if (user.getOnlineStatus() == OnlineStatus.ONLINE) {
                        onlineMember.add(user);
                    } else if (user.getOnlineStatus() == OnlineStatus.IDLE) {
                        afkMembers.add(user);
                    } else if (user.getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {
                        doNotDisturbUsers.add(user);
                    } else if (user.getOnlineStatus() == OnlineStatus.UNKNOWN) {
                        unknownMembers.add(user);
                    }
                }
            });

            if (onlineMember.size() + afkMembers.size() + unknownMembers.size() > 0) {
                if (onlineMember.size() > 0) {
                    result += "Membres en ligne (" + (onlineMember.size() + afkMembers.size()) + ") :\n**DISPONIBLES :**";
                    for (Member member : onlineMember) {
                        result += "\n" + member.getUser().getName();
                    }

                    result += "\n\n";
                }

                if (afkMembers.size() > 0) {
                    result += "**ABSENTS :**";
                    for (Member member : afkMembers) {
                        result += "\n" + member.getUser().getName();
                    }
                }

                if (afkMembers.size() > 0) {
                    result += "**NE PAS DERANGER :**";
                    for (Member member : doNotDisturbUsers) {
                        result += "\n" + member.getUser().getName();
                    }
                }

                if (unknownMembers.size() > 0) {
                    result += "**AUTRES :**";
                    for (Member member : unknownMembers) {
                        result += "\n" + member.getUser().getName();
                    }
                }

                return result;
            } else {
                return "Il n'y a aucun membres en ligne ! (hormis vous et le bot)";
            }
        } else {
            throw new CommandException("Can only be fired in a guild");
        }
    }
}
