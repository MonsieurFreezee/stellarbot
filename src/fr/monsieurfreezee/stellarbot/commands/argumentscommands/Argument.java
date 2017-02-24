package fr.monsieurfreezee.stellarbot.commands.argumentscommands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import static fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentType.*;

public class Argument {

    private Object value;
    private ArgumentType type;

    // Text case
    public Argument(String text) {
        value = text;
        type = TEXT;
    }

    public String getText() {
        if (type == TEXT) {
            return (String) value;
        } else {
            return null;
        }
    }

    // User case
    public Argument(User user) {
        value = user;
        type = USER;
    }

    public User getUser() {
        if (type == USER) {
            return (User) value;
        } else {
            return null;
        }
    }

    // Text channel case
    public Argument(TextChannel channel) {
        value = channel;
        type = ArgumentType.TEXTCHANNEL;
    }

    public TextChannel getTextChannel() {
        if (type == TEXTCHANNEL) {
            return (TextChannel) value;
        } else {
            return null;
        }
    }

    // Integer case
    public Argument(Integer integerNumber) {
        value = integerNumber;
        type = ArgumentType.INTEGER;
    }

    public Integer getInteger() {
        if (type == INTEGER) {
            return (Integer) value;
        } else {
            return null;
        }
    }

    // Float case
    public Argument(Float floatNumber) {
        value = floatNumber;
        type = ArgumentType.FLOAT;
    }

    public Float getFloat() {
        if (type == FLOAT) {
            return (Float) value;
        } else {
            return null;
        }
    }
}
