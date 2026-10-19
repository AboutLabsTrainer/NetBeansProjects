package de.ibb.tag4;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Level 1 – Einfache JTable-Demo.
 * <p>
 * Anforderungen:
 * - JTable mit 3 Spalten und mindestens 5 Beispielzeilen
 * - Sortierung aktivieren
 * - Bei Klick: Ausgewählte Zeile in der Konsole ausgeben
 */
public class Level1TableDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Level1TableDemo::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Level 1 – Einfache JTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Spalten definieren
        String[] spalten = {"ID", "Name", "Alter"};

        // Model erstellen
        DefaultTableModel model = new DefaultTableModel(spalten, 0);

        // Beispielzeilen hinzufügen (mindestens 5)
        model.addRow(new Object[]{1, "Max", 25});
        model.addRow(new Object[]{2, "Lisa", 30});
        model.addRow(new Object[]{3, "Jonas", 22});
        model.addRow(new Object[]{4, "Anna", 28});
        model.addRow(new Object[]{5, "Tom", 35});

        // JTable mit Model
        JTable table = new JTable(model);

        // WICHTIG: JTable immer in ein JScrollPane packen,
        // damit der Header (Spaltennamen) angezeigt wird.
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Wichtige JTable-Konfiguration gemäß Kursbeschreibung
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true); // automatische Sortierung aktivieren

        // Spaltenbreiten-Beispiel
        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        // Selection Handling – bei Klick ausgewählte Zeile in Konsole ausgeben
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Nur reagieren, wenn Auswahl "fertig" ist
                if (e.getValueIsAdjusting()) {
                    return;
                }

                int viewRow = table.getSelectedRow();
                if (viewRow < 0) {
                    return; // keine Auswahl
                }

                // WICHTIG: Bei aktivem RowSorter ist View-Zeile != Model-Zeile
                int modelRow = table.convertRowIndexToModel(viewRow);

                Object id = model.getValueAt(modelRow, 0);
                Object name = model.getValueAt(modelRow, 1);
                Object alter = model.getValueAt(modelRow, 2);

                System.out.println("Ausgewählt: ID=" + id
                        + ", Name=" + name
                        + ", Alter=" + alter);
            }
        });

        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
