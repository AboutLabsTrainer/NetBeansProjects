package de.ibb.sync;

public class SichererZaehler {

    private int stand;

    public synchronized void erhoehen() {
        // TODO stand um 1
    }

    public synchronized int wert() {
        // TODO aktuellen Stand
        return 0;
    }

    public static int parallel(int threads, int proThread) throws InterruptedException {
        // TODO threads mal proThread Erhöhungen
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(parallel(8, 1000));
    }
}
