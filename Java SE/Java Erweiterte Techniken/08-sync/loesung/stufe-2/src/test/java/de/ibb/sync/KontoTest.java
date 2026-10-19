package de.ibb.sync;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KontoTest {

    @Test
    void vieleKleineUeberweisungenBleibenInDerSumme() throws InterruptedException {
        Konto a = new Konto("A", 1000);
        Konto b = new Konto("B", 0);
        ExecutorService pool = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < 4; i++) {
                pool.submit(() -> {
                    for (int n = 0; n < 250; n++) {
                        Konto.ueberweisen(a, b, 1);
                    }
                });
            }
        } finally {
            pool.shutdown();
        }
        assertTrue(pool.awaitTermination(10, TimeUnit.SECONDS));
        assertEquals(0, a.stand());
        assertEquals(1000, b.stand());
    }

    @Test
    void gegenlaeufigeBetraege() throws InterruptedException {
        Konto a = new Konto("A", 100);
        Konto b = new Konto("B", 40);
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(() -> Konto.ueberweisen(a, b, 30)));
        threads.add(new Thread(() -> Konto.ueberweisen(b, a, 10)));
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join(5000);
        }
        assertEquals(80, a.stand());
        assertEquals(60, b.stand());
    }
}
