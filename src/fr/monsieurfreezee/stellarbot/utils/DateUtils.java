package fr.monsieurfreezee.stellarbot.utils;

import java.time.OffsetDateTime;
import java.util.Calendar;

public class DateUtils {
    public static String getHour() {
        Calendar time = Calendar.getInstance();

        String hourOfDay = String.valueOf(time.get(Calendar.HOUR_OF_DAY));
        if (hourOfDay.length() == 1) {
            hourOfDay = "0" + hourOfDay;
        }

        String minute = String.valueOf(time.get(Calendar.MINUTE));
        if (minute.length() == 1) {
            minute = "0" + minute;
        }

        String seconds = String.valueOf(time.get(Calendar.SECOND));
        if (seconds.length() == 1) {
            seconds = "0" + seconds;
        }

        return hourOfDay + ":" + minute + ":" + seconds;
    }

    public static String getDate() {

        Calendar time = Calendar.getInstance();

        String dayOfMonth = String.valueOf(time.get(Calendar.DAY_OF_MONTH));
        if (dayOfMonth.length() == 1) {
            dayOfMonth = "0" + dayOfMonth;
        }

        String month = String.valueOf(time.get(Calendar.MONTH));
        if (month.length() == 1) {
            month = "0" + month;
        }

        String year = String.valueOf(time.get(Calendar.YEAR));
        return dayOfMonth + "/" + month + "/" + year;
    }

    public static String getHour(OffsetDateTime date) {
        String hourOfDay = String.valueOf(date.getHour());
        if (hourOfDay.length() == 1) {
            hourOfDay = "0" + hourOfDay;
        }

        String minute = String.valueOf(date.getMinute());
        if (minute.length() == 1) {
            minute = "0" + minute;
        }

        String seconds = String.valueOf(date.getSecond());
        if (seconds.length() == 1) {
            seconds = "0" + seconds;
        }

        return hourOfDay + ":" + minute + ":" + seconds;
    }

    public static String getDate(OffsetDateTime date) {
        String dayOfMonth = String.valueOf(date.getDayOfMonth());
        if (dayOfMonth.length() == 1) {
            dayOfMonth = "0" + dayOfMonth;
        }

        String month = String.valueOf(date.getMonthValue());
        if (month.length() == 1) {
            month = "0" + month;
        }

        String year = String.valueOf(date.getYear());
        return dayOfMonth + "/" + month + "/" + year;
    }
}
