package fr.monsieurfreezee.stellarbot.utils;

public class Log {

    /* INFOS & ERRORS */
    public static void info(String type, String message) {
        System.out.println("[" + DateUtils.getHour() + "] [Info] [" + type.toUpperCase() + "]: " + message);
    }

    public static void error(String type, String error) {
        System.err.println("[" + DateUtils.getHour() + "] [Error] [" + type.toUpperCase() + "]: " + error);
    }
}
