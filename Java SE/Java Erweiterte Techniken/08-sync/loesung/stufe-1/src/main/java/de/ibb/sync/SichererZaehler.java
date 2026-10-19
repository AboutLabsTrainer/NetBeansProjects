package de.ibb.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SichererZaehler {

    private int stand;

    public synchronized void erhoehen() {
        stand++;
    }

    public synchronized int wert() {
        return stand;
    }

    public static int parallel(int threads, int proThread) throws InterruptedException {
        SichererZaehler zaehler = new SichererZaehler();
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        try {
            List<Callable<Void>> aufgaben = new ArrayList<>();
            for (int i = 0; i < threads; i++) {
                aufgaben.add(() -> {
                    for (int n = 0; n < proThread; n++) {
                        zaehler.erhoehen();
                    }
                    return null;
                });
            }
            pool.invokeAll(aufgaben);
            return zaehler.wert();
        } finally {
            pool.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(parallel(8, 1000));
    }
}
