package de.ibb.tag09;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Producer-Consumer-Demo mit BlockingQueue.
 */
public class Level2BlockingQueueDemo {

    private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    private class Producer implements Runnable {
        private final int id;

        private Producer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    int value = id * 100 + i;
                    queue.put(value);
                    System.out.println("Producer " + id + " produziert: " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class Consumer implements Runnable {
        private final int id;

        private Consumer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    Integer value = queue.take();
                    System.out.println("Consumer " + id + " konsumiert: " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void runDemo() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Producer(i), "Producer-" + i).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new Consumer(i), "Consumer-" + i).start();
        }
    }
}

