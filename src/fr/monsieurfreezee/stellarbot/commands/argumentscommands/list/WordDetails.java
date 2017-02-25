package fr.monsieurfreezee.stellarbot.commands.argumentscommands.list;

import fr.monsieurfreezee.stellarbot.commands.CommandDetails;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.Argument;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentCommand;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentType;
import fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentsList;
import fr.monsieurfreezee.stellarbot.utils.Lexicon;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;

@CommandDetails(name = "WordDetails", commandName = "WordDetails", description = "Renvoie plein de détails inintérssants d'un mot")
@ArgumentsList(arguments = {ArgumentType.TEXT})
public class WordDetails implements ArgumentCommand {

    @Override
    public String run(GenericMessageEvent event, Argument... arguments) {
        String word = arguments[0].getText();
        final fr.monsieurfreezee.stellarbot.utils.WordDetails[] wordDetails = new fr.monsieurfreezee.stellarbot.utils.WordDetails[1];
        wordDetails[0] = null;
        Lexicon.getLexicon().forEach((s, wd) -> {
            if (s.equalsIgnoreCase(word)) {
                wordDetails[0] = wd;
            }
        });
        if (wordDetails[0] != null) {
            return "**" + wordDetails[0].getWord() + "** :" +
                    "\nLemme : " + wordDetails[0].getLemma() +
                    "\nGenre (si non-verbe) : " + wordDetails[0].getGender() +
                    "\nNombre (si non-verbe) : " + wordDetails[0].getNumber() +
                    "\nFréquence d'utilisation (analyse de sous-titres de films) : " + Float.parseFloat(wordDetails[0].getMovieFrequency()) / 1_000_000 + "%" +
                    "\nFréquence d'utilisation (analyse de livres) : " + Float.parseFloat(wordDetails[0].getBookFrequency()) / 1_000_000 + "%" +
                    "\nFréquence d'utilisation du lemme (analyse de sous-titres de films) : " + Float.parseFloat(wordDetails[0].getMovieLemmaFrequency()) / 1_000_000 + "%" +
                    "\nFréquence d'utilisation du lemme (analyse de livres) : " + Float.parseFloat(wordDetails[0].getBookLemmaFrequency()) / 1_000_000 + "%" +
                    "\nDétails du verbe (si verbe) : " + wordDetails[0].getVerbDetails();
        } else {
            return "Le mot n'a pas pu être trouvé parmis les quelques 142 694 mots... quel dommage !";
        }
    }
}
