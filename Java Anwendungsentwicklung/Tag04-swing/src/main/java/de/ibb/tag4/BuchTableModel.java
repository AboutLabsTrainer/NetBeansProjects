package de.ibb.tag4;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Eigenes TableModel für {@link Buch}.
 * <p>
 * Zeigt, wie man ein typisiertes Model mit korrekten Spaltentypen und
 * Editierbarkeit (inkl. Boolean-Checkbox) implementiert.
 */
public class BuchTableModel extends AbstractTableModel {

    private final List<Buch> buecher = new ArrayList<>();
    private final String[] spalten = {"Titel", "Autor", "Jahr", "Preis", "Gelesen"};

    @Override
    public int getRowCount() {
        return buecher.size();
    }

    @Override
    public int getColumnCount() {
        return spalten.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Buch buch = buecher.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> buch.getTitel();
            case 1 -> buch.getAutor();
            case 2 -> buch.getJahr();
            case 3 -> buch.getPreis();
            case 4 -> buch.isGelesen();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return spalten[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 1 -> String.class;
            case 2 -> Integer.class;
            case 3 -> Double.class; // korrekte numerische Sortierung
            case 4 -> Boolean.class; // Checkbox-Darstellung
            default -> Object.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Titel, Autor, Jahr, Preis, Gelesen editierbar – ID gäbe es hier nicht
        // Mindestens die Spalte "Gelesen" (Index 4) soll editierbar sein.
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Buch buch = buecher.get(rowIndex);
        switch (columnIndex) {
            case 0 -> buch.setTitel(aValue == null ? "" : aValue.toString());
            case 1 -> buch.setAutor(aValue == null ? "" : aValue.toString());
            case 2 -> {
                int jahr = 0;
                if (aValue instanceof Number) {
                    jahr = ((Number) aValue).intValue();
                } else if (aValue != null) {
                    try {
                        jahr = Integer.parseInt(aValue.toString());
                    } catch (NumberFormatException ignored) {
                        // ungültige Eingabe – Wert unverändert lassen
                        return;
                    }
                }
                buch.setJahr(jahr);
            }
            case 3 -> {
                double preis = 0.0;
                if (aValue instanceof Number) {
                    preis = ((Number) aValue).doubleValue();
                } else if (aValue != null) {
                    try {
                        preis = Double.parseDouble(aValue.toString().replace(',', '.'));
                    } catch (NumberFormatException ignored) {
                        return;
                    }
                }
                buch.setPreis(preis);
            }
            case 4 -> {
                boolean gelesen = false;
                if (aValue instanceof Boolean) {
                    gelesen = (Boolean) aValue;
                } else if (aValue != null) {
                    gelesen = Boolean.parseBoolean(aValue.toString());
                }
                buch.setGelesen(gelesen);
            }
            default -> {
                return;
            }
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addBuch(Buch buch) {
        int index = buecher.size();
        buecher.add(buch);
        fireTableRowsInserted(index, index);
    }

    public void removeBuch(int index) {
        if (index < 0 || index >= buecher.size()) {
            return;
        }
        buecher.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public Buch getBuchAt(int index) {
        if (index < 0 || index >= buecher.size()) {
            return null;
        }
        return buecher.get(index);
    }
}
