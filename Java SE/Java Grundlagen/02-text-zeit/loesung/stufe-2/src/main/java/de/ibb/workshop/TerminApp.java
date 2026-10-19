package de.ibb.workshop;
import java.time.LocalDateTime;
public class TerminApp {
    public static void main(String[] args) {
        LocalDateTime start = Termin.beginn(2026, 3, 15, 9, 30);
        System.out.println(Termin.deutsch(start));
        System.out.println(Termin.deutsch(Termin.erinnerung(start)));
    }
}
