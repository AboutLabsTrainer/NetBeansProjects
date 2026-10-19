package de.ibb.sockets;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EchoProtokollTest {

    @Test
    void echoByeUndId() {
        assertEquals("Echo: Hallo", EchoProtokoll.antwort("Hallo"));
        assertTrue(EchoProtokoll.beenden("bye"));
        assertTrue(EchoProtokoll.beenden("BYE"));
        assertFalse(EchoProtokoll.beenden("hallo"));
        assertFalse(EchoProtokoll.beenden(null));
        assertFalse(EchoProtokoll.beenden(" bye"));
        assertEquals(Optional.of("42"), EchoProtokoll.idAusJson("{\"id\": 42, \"title\": \"x\"}"));
        assertEquals(Optional.empty(), EchoProtokoll.idAusJson("{}"));
    }
}
