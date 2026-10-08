package de.ibb.tag08.level3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Einfaches Producer-Consumer-Beispiel mit ExecutorService.
 */
public class ProducerConsumerDemo {

    public void runDemo() throws InterruptedException {
        System.out.println("  Producer-Consumer-Demo:");

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable producer = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String item = "Item-" + i;
                    queue.put(item);
                    System.out.println("    Producer legt " + item + " in die Queue");
                    Thread.sleep(100);
                }
                queue.put("END");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                while (true) {
                    String item = queue.take();
                    if ("END".equals(item)) {
                        queue.put("END"); // für weiteren Consumer
                        break;
                    }
                    System.out.println("    Consumer verarbeitet " + item
                            + " in " + Thread.currentThread().getName());
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        executor.execute(producer);
        executor.execute(consumer);
        executor.execute(consumer);

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}

