package de.ibb.sockets;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatRaumTest {

    @Test
    void beitrittNachrichtUndQuit() {
        ChatRaum raum = new ChatRaum();
        assertTrue(raum.beitreten("A"));
        assertTrue(raum.beitreten("B"));
        assertTrue(raum.beitreten("C"));
        assertFalse(raum.beitreten("A"));

        assertEquals(List.of("A", "B"), raum.beitrittsEmpfaenger("C"));
        assertEquals(List.of("A", "B", "C"), raum.nachrichtenEmpfaenger());
        assertEquals("[pool-1-thread-1] Hallo", raum.zeile("pool-1-thread-1", "Hallo"));
        assertTrue(raum.istQuit(" /quit "));
        assertTrue(raum.istQuit("/QUIT"));
        assertFalse(raum.istQuit("quit"));

        assertTrue(raum.verlassen("B"));
        assertEquals(List.of("A", "C"), raum.nachrichtenEmpfaenger());
    }
}
