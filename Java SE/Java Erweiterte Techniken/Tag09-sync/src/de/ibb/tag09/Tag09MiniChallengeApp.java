package de.ibb.tag09;

/**
 * Einstiegspunkt für alle Mini-Challenge-Demos von Tag 9.
 */
public class Tag09MiniChallengeApp {

    public static void main(String[] args) {
        Tag09MiniChallengeApp app = new Tag09MiniChallengeApp();
        System.out.println("=== Tag 9: Multithreading – Synchronisation Mini-Challenge ===");
        app.runLevel1Demos();
        app.runLevel2Demos();
        app.runLevel3Demos();
        System.out.println("=== Ende aller Demos ===");
    }

    private void runLevel1Demos() {
        System.out.println("\n--- Level 1: Einsteiger ---");
        demoSynchronizedCounter();
        demoAtomicCounter();
        demoThreadSafeValue();
    }

    private void demoSynchronizedCounter() {
        System.out.println("\n[Level 1] Synchronized Counter");
        Level1SynchronizedCounter counter = new Level1SynchronizedCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task, "SyncCounter-1");
        Thread t2 = new Thread(task, "SyncCounter-2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Erwarteter Wert: 2000, tatsächlicher Wert: " + counter.getValue());
    }

    private void demoAtomicCounter() {
        System.out.println("\n[Level 1] AtomicInteger Counter");
        Level1AtomicCounter counter = new Level1AtomicCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task, "AtomicCounter-1");
        Thread t2 = new Thread(task, "AtomicCounter-2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Erwarteter Wert: 2000, tatsächlicher Wert: " + counter.getValue());
    }

    private void demoThreadSafeValue() {
        System.out.println("\n[Level 1] Thread-sichere Getter/Setter-Klasse");
        Level1ThreadSafeValue valueHolder = new Level1ThreadSafeValue();

        Runnable writer = () -> {
            for (int i = 0; i < 10; i++) {
                valueHolder.setValue(i);
                System.out.println(Thread.currentThread().getName() + " setzt Wert auf " + i);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable reader = () -> {
            for (int i = 0; i < 10; i++) {
                int current = valueHolder.getValue();
                System.out.println(Thread.currentThread().getName() + " liest Wert: " + current);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().getName();
                }
            }
        };

        Thread writerThread = new Thread(writer, "Writer");
        Thread readerThread = new Thread(reader, "Reader");
        writerThread.start();
        readerThread.start();
        try {
            writerThread.join();
            readerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void runLevel2Demos() {
        System.out.println("\n--- Level 2: Fortgeschritten ---");
        demoLockStack();
        demoReadWriteCache();
        demoBlockingQueue();
    }

    private void demoLockStack() {
        System.out.println("\n[Level 2] Thread-sicherer Stack mit ReentrantLock");
        Level2LockStack<Integer> stack = new Level2LockStack<>();

        Runnable pusher = () -> {
            for (int i = 0; i < 50; i++) {
                stack.push(i);
            }
        };

        Runnable popper = () -> {
            for (int i = 0; i < 50; i++) {
                stack.pop();
            }
        };

        Thread t1 = new Thread(pusher, "Pusher");
        Thread t2 = new Thread(popper, "Popper");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Stack-Größe nach Operationen: " + stack.size());
    }

    private void demoReadWriteCache() {
        System.out.println("\n[Level 2] Cache mit ReadWriteLock");
        Level2ReadWriteCache cache = new Level2ReadWriteCache();

        Runnable writer = () -> {
            for (int i = 0; i < 10; i++) {
                cache.put("key" + i, "value" + i);
                System.out.println("Schreiber speichert key" + i);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable reader = () -> {
            for (int i = 0; i < 10; i++) {
                String value = cache.get("key" + i);
                System.out.println("Leser liest key" + i + " -> " + value);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread writerThread = new Thread(writer, "Cache-Writer");
        Thread readerThread = new Thread(reader, "Cache-Reader");
        writerThread.start();
        readerThread.start();
        try {
            writerThread.join();
            readerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void demoBlockingQueue() {
        System.out.println("\n[Level 2] Producer-Consumer mit BlockingQueue");
        Level2BlockingQueueDemo demo = new Level2BlockingQueueDemo();
        demo.runDemo();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void runLevel3Demos() {
        System.out.println("\n--- Level 3: Profi ---");
        demoDeadlock();
        demoConnectionPool();
        demoCountDownLatch();
    }

    private void demoDeadlock() {
        System.out.println("\n[Level 3] Deadlock-Demo (kann hängen bleiben!)");
        Level3DeadlockDemo deadlockDemo = new Level3DeadlockDemo();
        deadlockDemo.runDeadlockDemo();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[Level 3] Sichere Variante mit tryLock");
        deadlockDemo.sichereMethode();
    }

    private void demoConnectionPool() {
        System.out.println("\n[Level 3] ConnectionPool mit Semaphore");
        Level3ConnectionPool pool = new Level3ConnectionPool(3);
        pool.runDemo(10);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void demoCountDownLatch() {
        System.out.println("\n[Level 3] Parallele Initialisierung mit CountDownLatch");
        Level3InitWithCountDownLatch latchDemo = new Level3InitWithCountDownLatch();
        latchDemo.runDemo(5);
    }
}

