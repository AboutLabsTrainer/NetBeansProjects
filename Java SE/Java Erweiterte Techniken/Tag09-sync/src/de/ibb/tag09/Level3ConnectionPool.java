package de.ibb.tag09;

import java.util.concurrent.Semaphore;

/**
 * Einfacher ConnectionPool mit Semaphore.
 */
public class Level3ConnectionPool {

    private final Semaphore semaphore;

    public Level3ConnectionPool(int maxConnections) {
        this.semaphore = new Semaphore(maxConnections);
    }

    public void useConnection() {
        try {
            semaphore.acquire();
            try {
                System.out.println("Verbindung genutzt von " + Thread.currentThread().getName());
                Thread.sleep(200);
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void runDemo(int totalClients) {
        for (int i = 0; i < totalClients; i++) {
            Thread t = new Thread(this::useConnection, "Client-" + i);
            t.start();
        }
    }
}

