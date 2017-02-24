package fr.monsieurfreezee.stellarbot.listeners;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class RTListener implements EventListener {

    @Override
    public void onEvent(Event event) {
        if (event instanceof GuildMessageReceivedEvent) {
            GuildMessageReceivedEvent e = (GuildMessageReceivedEvent) event;
            if ((" " + e.getMessage().getContent().toLowerCase() + " ").contains(" rt ")) {
                e.getMessage().addReaction(e.getGuild().getEmotesByName("rt", true).get(0)).queue();
            }
        }
    }
}
