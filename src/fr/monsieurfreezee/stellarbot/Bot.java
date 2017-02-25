package fr.monsieurfreezee.stellarbot;

import fr.monsieurfreezee.stellarbot.commands.CommandsRegisterer;
import fr.monsieurfreezee.stellarbot.listeners.CommandListener;
import fr.monsieurfreezee.stellarbot.listeners.DisListener;
import fr.monsieurfreezee.stellarbot.listeners.ListenerManager;
import fr.monsieurfreezee.stellarbot.listeners.RTListener;
import fr.monsieurfreezee.stellarbot.utils.Lexicon;
import fr.monsieurfreezee.stellarbot.utils.Log;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Bot {

    public static Guild stellarGuild;
    public static JDA jda;

    public static void main(String[] args) throws IOException {
        try {
            jda = new JDABuilder(AccountType.BOT)
                    .setToken(args[0]) // Bot token must be put in program arguments
                    .addListener(new ListenerManager(
                            new CommandListener(),
                            new RTListener(),
                            new DisListener()))
                    .buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
            Log.error("Connection", "The bot failed to connect.");
            System.exit(-1);
        } catch (InterruptedException | RateLimitedException e) {
            e.printStackTrace();
        }

        jda.getSelfUser().getManager().setName("Stellar Bot");

        /*try {
            jda.getSelfUser().getManager().setAvatar(Icon.from(new File("src/gear-7.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //jda.getAccountManager().update();
        //jda.getAccountManager().setGame(BOT_VERSION);

        stellarGuild = jda.getGuildById("140218030117486592");

        // Static initialization
        new CommandsRegisterer();

        // Words list initialization
        new Lexicon();
    }
}
