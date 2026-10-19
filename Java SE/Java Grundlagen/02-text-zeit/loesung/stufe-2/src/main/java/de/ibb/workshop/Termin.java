package de.ibb.workshop;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Termin {
    private static final DateTimeFormatter DEUTSCH = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    public static LocalDateTime beginn(int jahr, int monat, int tag, int stunde, int minute) {
        return LocalDateTime.of(jahr, monat, tag, stunde, minute);
    }
    public static LocalDateTime erinnerung(LocalDateTime beginn) {
        return beginn.minusMinutes(30);
    }
    public static String deutsch(LocalDateTime zeit) {
        return zeit.format(DEUTSCH);
    }
}
