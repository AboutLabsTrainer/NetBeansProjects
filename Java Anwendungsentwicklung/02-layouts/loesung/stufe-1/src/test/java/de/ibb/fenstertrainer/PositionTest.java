package de.ibb.fenstertrainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    void pfeiltastenBewegenUmFuenf() {
        Position start = new Position(50, 50);
        assertEquals(45, start.bewege("links").getX());
        assertEquals(55, start.bewege("rechts").getX());
        assertEquals(45, start.bewege("hoch").getY());
        assertEquals(55, start.bewege("runter").getY());
        assertEquals(50, start.bewege("andere").getX());
    }
}
