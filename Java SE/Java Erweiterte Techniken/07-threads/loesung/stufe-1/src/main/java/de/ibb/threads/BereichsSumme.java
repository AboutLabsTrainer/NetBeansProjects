package de.ibb.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BereichsSumme {

    public static long summe(int von, int bisExklusiv, int teile) throws InterruptedException {
        if (teile < 1) {
            throw new IllegalArgumentException("teile");
        }
        int rest = Math.max(0, bisExklusiv - von);
        int basis = rest / teile;
        int extra = rest % teile;
        ExecutorService pool = Executors.newFixedThreadPool(Math.max(1, Math.min(teile, Math.max(rest, 1))));
        try {
            List<Callable<Long>> aufgaben = new ArrayList<>();
            int cursor = von;
            for (int i = 0; i < teile; i++) {
                int breite = basis + (i < extra ? 1 : 0);
                int start = cursor;
                int ende = cursor + breite;
                cursor = ende;
                aufgaben.add(() -> {
                    long teil = 0;
                    for (int n = start; n < ende; n++) {
                        teil += n;
                    }
                    return teil;
                });
            }
            long gesamt = 0;
            for (Future<Long> zukunft : pool.invokeAll(aufgaben)) {
                gesamt += zukunft.get();
            }
            return gesamt;
        } catch (ExecutionException ex) {
            throw new IllegalStateException(ex);
        } finally {
            pool.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(summe(1, 101, 4));
    }
}
