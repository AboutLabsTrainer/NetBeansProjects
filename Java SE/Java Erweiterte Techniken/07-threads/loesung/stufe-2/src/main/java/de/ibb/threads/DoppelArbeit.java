package de.ibb.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DoppelArbeit {

    public static long geradePlusUngerade(int n) throws InterruptedException {
        AtomicLong gerade = new AtomicLong();
        AtomicLong ungerade = new AtomicLong();
        CountDownLatch fertig = new CountDownLatch(2);

        Thread geradeThread = new Thread(() -> {
            try {
                long summe = 0;
                for (int i = 2; i <= n; i += 2) {
                    summe += i;
                }
                gerade.set(summe);
            } finally {
                fertig.countDown();
            }
        });
        Thread ungeradeThread = new Thread(() -> {
            try {
                long summe = 0;
                for (int i = 1; i <= n; i += 2) {
                    summe += i;
                }
                ungerade.set(summe);
            } finally {
                fertig.countDown();
            }
        });

        geradeThread.start();
        ungeradeThread.start();
        if (!fertig.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Threads nicht fertig");
        }
        return gerade.get() + ungerade.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(geradePlusUngerade(10));
    }
}
