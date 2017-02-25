package fr.monsieurfreezee.stellarbot.utils;

public class WordDetails {

    private String word, lemma, lexicalCategory, gender, number, movieFrequency, bookFrequency, movieLemmaFrequency, bookLemmaFrequency, verbDetails;

    public WordDetails(String mot, String lemma, String lexicalCategory, String gender, String number, String movieFrequency, String bookFrequency, String movieLemmaFrequency, String bookLemmaFrequency, String verbDetails) {
        this.word = mot;
        this.lemma = lemma;
        this.lexicalCategory = lexicalCategory;
        this.gender = gender;
        this.number = number;
        this.movieFrequency = movieFrequency;
        this.bookFrequency = bookFrequency;
        this.movieLemmaFrequency = movieLemmaFrequency;
        this.bookLemmaFrequency = bookLemmaFrequency;
        this.verbDetails = verbDetails;
    }

    public String getWord() { return word; }

    public String getLemma() { return lemma; }

    public String getLexicalCategory() { return lexicalCategory; }

    public String getGender() { return gender; }

    public String getNumber() { return number; }

    public String getMovieFrequency() { return movieFrequency; }

    public String getBookFrequency() { return bookFrequency; }

    public String getMovieLemmaFrequency() { return movieLemmaFrequency; }

    public String getBookLemmaFrequency() { return bookLemmaFrequency; }

    public String getVerbDetails() { return verbDetails; }
}
