package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {

    private static final DateTimeFormatter dateTimeForm = DateTimeFormatter.ofPattern("dd-MM-yyyy/HH:mm:ss");
    private String DateTimeString;
    private LocalDateTime DateTime;

    public void setDateTimeString(LocalDateTime DateTime) {
        DateTimeString = dateTimeForm.format(DateTime);
    }

    public String getDateTimeString() {
        DateTimeString = dateTimeForm.format(DateTime);
        return DateTimeString;
    }

    public void setDateTime(String str) {
        try {
            DateTime = LocalDateTime.parse(str, dateTimeForm);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты/времени");
        }
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTimeCurr() {
        DateTime = LocalDateTime.now();
    }

    public LocalDateTime getDateTimeCurr() {
        DateTimeString = dateTimeForm.format(LocalDateTime.now());
        return LocalDateTime.now();
    }

    public String getDateTimeStringCurr() {
        DateTimeString = dateTimeForm.format(LocalDateTime.now());
        return DateTimeString;
    }
}