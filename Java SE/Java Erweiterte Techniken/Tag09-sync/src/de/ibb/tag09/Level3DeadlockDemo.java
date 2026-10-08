package de.ibb.tag09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Demonstriert einen Deadlock und eine sichere Variante.
 */
public class Level3DeadlockDemo {

    private final Object lockA = new Object();
    private final Object lockB = new Object();

    public void methode1() {
        synchronized (lockA) {
            System.out.println("Thread 1: hat Lock A");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synchronized (lockB) {
                System.out.println("Thread 1: hat Lock B");
            }
        }
    }

    public void methode2() {
        synchronized (lockB) {
            System.out.println("Thread 2: hat Lock B");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synchronized (lockA) {
                System.out.println("Thread 2: hat Lock A");
            }
        }
    }

    // Sichere Variante mit konsistenter Lock-Reihenfolge / tryLock

    private final Lock reLockA = new ReentrantLock();
    private final Lock reLockB = new ReentrantLock();

    public void sichereMethode() {
        while (true) {
            boolean gotA = reLockA.tryLock();
            boolean gotB = false;
            try {
                if (gotA) {
                    gotB = reLockB.tryLock();
                    if (gotB) {
                        System.out.println("Beide Locks erhalten – kein Deadlock");
                        return;
                    }
                }
            } finally {
                if (gotB) {
                    reLockB.unlock();
                }
                if (gotA) {
                    reLockA.unlock();
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void runDeadlockDemo() {
        Thread t1 = new Thread(this::methode1, "Deadlock-1");
        Thread t2 = new Thread(this::methode2, "Deadlock-2");
        t1.start();
        t2.start();
    }
}

