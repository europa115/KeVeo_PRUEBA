package com.example.KeVeo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat DATE_FORMATER_2 = new SimpleDateFormat("yyyy-MM-dd");

    public static String dateToString(ZonedDateTime date) {
        return date != null ? date.format(DATE_FORMATER) : null;
    }

    public static ZonedDateTime stringToDate(String stringDate) {
        return ZonedDateTime.parse(stringDate, DATE_FORMATER.withZone(ZoneId.of("UTC")));
    }
    public static Date stringToDateSimple(String stringDate)  {

        try{

            Date date_formater=DATE_FORMATER_2.parse(stringDate);

            DATE_FORMATER_2.format(date_formater);

            return date_formater;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
