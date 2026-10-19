package de.ibb.fenstertrainer;

/** Pfeiltasten der Arbeitsfläche: Schrittweite 5, ohne JPanel. */
public final class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position bewege(String richtung) {
        // TODO: links, rechts, hoch, runter
        return this;
    }
}
