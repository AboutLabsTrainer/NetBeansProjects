package de.ibb.tag08.level1;

/**
 * Einfache Aufgabe, die von 1 bis 10 zählt und dazwischen pausiert.
 */
public class CountingTask implements Runnable {

    private final String name;

    public CountingTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s: Zähle %d%n", name, i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("%s: Unterbrochen%n", name);
                break;
            }
        }
        System.out.printf("%s: Fertig!%n", name);
    }
}

