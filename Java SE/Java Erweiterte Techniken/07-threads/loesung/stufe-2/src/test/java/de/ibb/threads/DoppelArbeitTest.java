package de.ibb.threads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoppelArbeitTest {

    @Test
    void geradeUndUngeradeErgebenDieDreieckszahl() throws InterruptedException {
        assertEquals(55, DoppelArbeit.geradePlusUngerade(10));
        assertEquals(1, DoppelArbeit.geradePlusUngerade(1));
    }
}
