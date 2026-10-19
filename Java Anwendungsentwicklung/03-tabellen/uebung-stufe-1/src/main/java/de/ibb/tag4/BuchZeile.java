package de.ibb.tag4;

/** Spaltenzugriff wie BuchTableModel.getValueAt, ohne JTable. */
public final class BuchZeile {

    private BuchZeile() {
    }

    public static Object wert(String titel, String autor, int jahr, double preis, boolean gelesen, int spalte) {
        // TODO: 0 Titel, 1 Autor, 2 Jahr, 3 Preis, 4 Gelesen
        return null;
    }
}
