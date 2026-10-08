package de.ibb.tag09;

import java.util.concurrent.CountDownLatch;

/**
 * Parallele Initialisierung mit CountDownLatch.
 */
public class Level3InitWithCountDownLatch {

    public void runDemo(int workerCount) {
        CountDownLatch latch = new CountDownLatch(workerCount);

        for (int i = 0; i < workerCount; i++) {
            final int id = i;
            new Thread(() -> {
                System.out.println("Worker " + id + " arbeitet...");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Worker " + id + " fertig!");
                latch.countDown();
            }, "Worker-" + id).start();
        }

        System.out.println("Warte auf alle Worker...");
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Alle Worker fertig!");
    }
}

