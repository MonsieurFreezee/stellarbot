package fr.monsieurfreezee.stellarbot.listeners;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class DisListener implements EventListener {

    @Override
    public void onEvent(Event event) {
        if (event instanceof MessageReceivedEvent) {
            MessageReceivedEvent e = (MessageReceivedEvent) event;

            if (e.getMessage().getContent().toLowerCase().startsWith("dis") || e.getMessage().getContent().toLowerCase().startsWith("dit")) {
                e.getChannel().sendMessage(e.getMessage().getContent().substring(3).trim()).queue();
            }
        }
    }
}
