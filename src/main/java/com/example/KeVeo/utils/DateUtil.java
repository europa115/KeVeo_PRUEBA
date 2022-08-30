package com.example.KeVeo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");



    public static String dateToString(LocalDateTime date) {
        return date != null ? date.format(DATE_FORMATER) : null;
    }

    public static LocalDateTime stringToDate(String stringDate) {
        return LocalDateTime.parse(stringDate, DATE_FORMATER.withZone(ZoneId.of("UTC")));
    }

}
