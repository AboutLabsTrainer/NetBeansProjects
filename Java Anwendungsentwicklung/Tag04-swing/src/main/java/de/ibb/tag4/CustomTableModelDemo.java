package de.ibb.tag4;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 * Level 3 – Demo für ein eigenes TableModel ({@link BuchTableModel}).
 * <p>
 * Anforderungen:
 * - Eigene Klasse (Buch mit Titel, Autor, Jahr, Preis, gelesen)
 * - Eigenes TableModel
 * - Boolean-Spalte „Gelesen" mit Checkbox
 * - Optional: einfache CRUD-Funktionen
 */
public class CustomTableModelDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CustomTableModelDemo::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Level 3 – Custom TableModel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        BuchTableModel model = new BuchTableModel();

        // Beispiel-Daten
        model.addBuch(new Buch("Clean Code", "Robert C. Martin", 2008, 39.99, true));
        model.addBuch(new Buch("Effective Java", "Joshua Bloch", 2018, 45.00, false));
        model.addBuch(new Buch("Java Concurrency in Practice", "Goetz", 2006, 49.90, false));

        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        // Dank getColumnClass() funktionieren Sortierung und Checkbox-Anzeige korrekt
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Einfache CRUD-Eingabe unten
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titelLabel = new JLabel("Titel:");
        JTextField titelField = new JTextField(15);
        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField(15);
        JLabel jahrLabel = new JLabel("Jahr:");
        JTextField jahrField = new JTextField(5);
        JLabel preisLabel = new JLabel("Preis:");
        JTextField preisField = new JTextField(6);

        JButton addButton = new JButton("Buch hinzufügen");
        JButton deleteButton = new JButton("Buch löschen");

        int col = 0;
        gbc.gridy = 0;
        gbc.gridx = col++;
        inputPanel.add(titelLabel, gbc);
        gbc.gridx = col++;
        inputPanel.add(titelField, gbc);
        gbc.gridx = col++;
        inputPanel.add(autorLabel, gbc);
        gbc.gridx = col++;
        inputPanel.add(autorField, gbc);

        gbc.gridy = 1;
        col = 0;
        gbc.gridx = col++;
        inputPanel.add(jahrLabel, gbc);
        gbc.gridx = col++;
        inputPanel.add(jahrField, gbc);
        gbc.gridx = col++;
        inputPanel.add(preisLabel, gbc);
        gbc.gridx = col++;
        inputPanel.add(preisField, gbc);

        gbc.gridy = 2;
        col = 0;
        gbc.gridx = col++;
        inputPanel.add(addButton, gbc);
        gbc.gridx = col++;
        inputPanel.add(deleteButton, gbc);

        frame.add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titel = titelField.getText().trim();
                String autor = autorField.getText().trim();
                String jahrText = jahrField.getText().trim();
                String preisText = preisField.getText().trim().replace(',', '.');

                if (titel.isEmpty() || autor.isEmpty() || jahrText.isEmpty() || preisText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Alle Felder (Titel, Autor, Jahr, Preis) müssen ausgefüllt sein.",
                            "Eingabefehler",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int jahr;
                double preis;
                try {
                    jahr = Integer.parseInt(jahrText);
                    preis = Double.parseDouble(preisText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Jahr muss eine ganze Zahl und Preis eine Zahl sein.",
                            "Eingabefehler",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Neues Buch standardmäßig als "nicht gelesen"
                Buch buch = new Buch(titel, autor, jahr, preis, false);
                model.addBuch(buch);

                titelField.setText("");
                autorField.setText("");
                jahrField.setText("");
                preisField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow < 0) {
                    JOptionPane.showMessageDialog(frame,
                            "Bitte wählen Sie ein Buch zum Löschen aus.",
                            "Keine Auswahl",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int result = JOptionPane.showConfirmDialog(frame,
                        "Soll das ausgewählte Buch wirklich gelöscht werden?",
                        "Löschen bestätigen",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (result != JOptionPane.YES_OPTION) {
                    return;
                }

                int modelRow = table.convertRowIndexToModel(viewRow);
                model.removeBuch(modelRow);
            }
        });

        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
