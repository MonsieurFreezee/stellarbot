package fr.monsieurfreezee.stellarbot.listeners;

import fr.monsieurfreezee.stellarbot.Bot;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ListenerManager implements EventListener {

    private static ArrayList<EventListener> listeners = new ArrayList<>();

    public ListenerManager(EventListener... events) {
        Collections.addAll(listeners, events);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof MessageReceivedEvent) {
            MessageReceivedEvent e = (MessageReceivedEvent) event;
            if (e.getAuthor() == Bot.jda.getSelfUser()) {
                return;
            }
        }

        for (EventListener listener: listeners) {
            listener.onEvent(event);
        }
    }

    public static void addListener(EventListener listener) {
        listeners.add(listener);
    }
}
