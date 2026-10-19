package de.ibb.fenstertrainer;

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
        int schritt = 5;
        return switch (richtung) {
            case "links" -> new Position(x - schritt, y);
            case "rechts" -> new Position(x + schritt, y);
            case "hoch" -> new Position(x, y - schritt);
            case "runter" -> new Position(x, y + schritt);
            default -> this;
        };
    }
}
