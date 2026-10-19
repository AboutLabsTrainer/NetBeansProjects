package de.ibb.workshop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

/**
 * Übungen zur Date-Time-API.
 *
 * Themen:
 * - LocalDate, LocalTime, LocalDateTime
 * - Period und Duration
 * - DateTimeFormatter (format und parse)
 */
public class DateTimeWorkshop {

    public static void main(String[] args) {

        // ------------------------------------------------------------
        // Aufgabe 1: Heutiges Datum und aktuelle Zeit
        // ------------------------------------------------------------

        LocalDate today = LocalDate.now();
        LocalTime now   = LocalTime.now();
        LocalDateTime nowDateTime = LocalDateTime.now();

        System.out.println("=== Aufgabe 1: Heute & Jetzt ===");
        System.out.println("Heute (LocalDate):       " + today);
        System.out.println("Jetzt (LocalTime):       " + now);
        System.out.println("Heute+Jetzt (DateTime):  " + nowDateTime);

        // Kommentar:
        // - LocalDate verarbeitet nur Datum
        // - LocalTime verarbeitet nur Uhrzeit
        // - LocalDateTime verarbeitet Datum UND Uhrzeit

        // ------------------------------------------------------------
        // Aufgabe 2: Geburtstag & Alter
        // ------------------------------------------------------------

        // Beispiel-Geburtsdatum – im Unterricht von den TN anpassen lassen
        LocalDate birthday = LocalDate.of(1990, 1, 1);

        Period age = Period.between(birthday, today);

        System.out.println();
        System.out.println("=== Aufgabe 2: Alter berechnen ===");
        System.out.println("Geburtstag: " + birthday);
        System.out.println("Heute:      " + today);
        System.out.println("Du bist " + age.getYears() + " Jahre, "
                + age.getMonths() + " Monate und "
                + age.getDays() + " Tage alt.");

        // ------------------------------------------------------------
        // Aufgabe 3: Terminplaner
        // ------------------------------------------------------------

        LocalDate meetingDate = LocalDate.of(2026, 3, 15);
        LocalTime meetingTime = LocalTime.of(9, 30);
        LocalDateTime meeting = LocalDateTime.of(meetingDate, meetingTime);

        // Erinnerung 30 Minuten vorher
        LocalDateTime reminder = meeting.minusMinutes(30);

        // Dauer des Termins: 90 Minuten
        Duration duration = Duration.ofMinutes(90);
        LocalDateTime end = meeting.plus(duration);

        System.out.println();
        System.out.println("=== Aufgabe 3: Terminplaner ===");
        System.out.println("Termin-Beginn:   " + meeting);
        System.out.println("Erinnerung um:   " + reminder);
        System.out.println("Termin-Ende:     " + end);

        // ------------------------------------------------------------
        // Aufgabe 4: Formatieren & Parsen mit DateTimeFormatter
        // ------------------------------------------------------------

        System.out.println();
        System.out.println("=== Aufgabe 4: Formatieren & Parsen ===");

        DateTimeFormatter germanFormatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        String formattedMeeting = meeting.format(germanFormatter);
        System.out.println("Termin (deutsch formatiert): " + formattedMeeting);

        // String -> LocalDateTime (parsen)
        String input = "24.12.2025 18:30";
        LocalDateTime christmas = LocalDateTime.parse(input, germanFormatter);

        System.out.println("Geparstes Christmas-DateTime: " + christmas);

        // Anderes Format für Ausgabe
        DateTimeFormatter longFormatter =
                DateTimeFormatter.ofPattern("EEEE, dd. MMMM yyyy HH:mm");

        String christmasNice = christmas.format(longFormatter);
        System.out.println("Weihnachten schön formatiert: " + christmasNice);

        // Kommentar:
        // - Ein DateTimeFormatter beschreibt ein Formatmuster (Pattern).
        // - format(...) wandelt ein Date/Time-Objekt in einen String um.
        // - parse(...) wandelt einen String zurück in ein Date/Time-Objekt.
    }
}
