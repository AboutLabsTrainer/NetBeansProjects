package de.ibb.workshop;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
class TerminTest {
    @Test void plantUndFormatiert() {
        LocalDateTime start = Termin.beginn(2026, 3, 15, 9, 30);
        assertEquals(LocalDateTime.of(2026, 3, 15, 9, 0), Termin.erinnerung(start));
        assertEquals("15.03.2026 09:30", Termin.deutsch(start));
        assertEquals("15.03.2026 09:00", Termin.deutsch(Termin.erinnerung(start)));
    }
}
