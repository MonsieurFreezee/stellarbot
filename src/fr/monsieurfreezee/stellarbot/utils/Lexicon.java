package fr.monsieurfreezee.stellarbot.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;

public class Lexicon {

    private static HashMap<String, WordDetails> lexicon;

    static {
        lexicon = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/lexicon.txt"), Charset.forName("UTF-8")));
            reader.readLine(); // Discard file header

            String line;

            while ((line = reader.readLine()) != null) {
                String[] splitWord = line.split("\t");
                lexicon.put(splitWord[0], new WordDetails(splitWord[0], splitWord[2], splitWord[3], splitWord[4], splitWord[5], splitWord[8], splitWord[9], splitWord[6], splitWord[7], splitWord[10]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, WordDetails> getLexicon() { return lexicon; }
}
