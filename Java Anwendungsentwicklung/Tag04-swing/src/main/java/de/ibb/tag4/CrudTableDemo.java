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
import javax.swing.table.DefaultTableModel;

/**
 * Level 2 – CRUD-Tabelle mit Eingabefeldern.
 * <p>
 * Anforderungen:
 * - Zeilen hinzufügen, bearbeiten, löschen
 * - Eingabefelder für neue/zu bearbeitende Daten
 * - Bestätigung vor Löschen
 */
public class CrudTableDemo {

    private int nextId = 1;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CrudTableDemo().createAndShowGui());
    }

    private void createAndShowGui() {
        JFrame frame = new JFrame("Level 2 – CRUD-Tabelle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Spalten definieren
        String[] spalten = {"ID", "Name", "Alter"};
        DefaultTableModel model = new DefaultTableModel(spalten, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Bearbeitung nur über die Eingabefelder erlauben, nicht direkt in der Tabelle
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Eingabe-Panel unten
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        JLabel ageLabel = new JLabel("Alter:");
        JTextField ageField = new JTextField(5);

        JButton addButton = new JButton("Hinzufügen");
        JButton updateButton = new JButton("Bearbeiten");
        JButton deleteButton = new JButton("Löschen");

        int col = 0;
        gbc.gridx = col++;
        gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);

        gbc.gridx = col++;
        inputPanel.add(nameField, gbc);

        gbc.gridx = col++;
        inputPanel.add(ageLabel, gbc);

        gbc.gridx = col++;
        inputPanel.add(ageField, gbc);

        gbc.gridy = 1;
        col = 0;
        gbc.gridx = col++;
        inputPanel.add(addButton, gbc);

        gbc.gridx = col++;
        inputPanel.add(updateButton, gbc);

        gbc.gridx = col++;
        inputPanel.add(deleteButton, gbc);

        frame.add(inputPanel, BorderLayout.SOUTH);

        // Button-Logik
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();

                if (name.isEmpty() || ageText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Name und Alter dürfen nicht leer sein.",
                            "Eingabefehler",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Alter muss eine ganze Zahl sein.",
                            "Eingabefehler",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Object[] row = {nextId++, name, age};
                model.addRow(row);

                // Felder leeren
                nameField.setText("");
                ageField.setText("");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow < 0) {
                    JOptionPane.showMessageDialog(frame,
                            "Bitte wählen Sie eine Zeile zum Bearbeiten aus.",
                            "Keine Auswahl",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int modelRow = table.convertRowIndexToModel(viewRow);

                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();

                if (name.isEmpty() || ageText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Name und Alter dürfen nicht leer sein.",
                            "Eingabefehler",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Alter muss eine ganze Zahl sein.",
                            "Eingabefehler",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                model.setValueAt(name, modelRow, 1);
                model.setValueAt(age, modelRow, 2);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow < 0) {
                    JOptionPane.showMessageDialog(frame,
                            "Bitte wählen Sie eine Zeile zum Löschen aus.",
                            "Keine Auswahl",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int result = JOptionPane.showConfirmDialog(frame,
                        "Soll die ausgewählte Zeile wirklich gelöscht werden?",
                        "Löschen bestätigen",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (result != JOptionPane.YES_OPTION) {
                    return;
                }

                int modelRow = table.convertRowIndexToModel(viewRow);
                model.removeRow(modelRow);
            }
        });

        // Wenn eine Zeile in der Tabelle gewählt wird, Felder mit deren Inhalt füllen
        table.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int viewRow = table.getSelectedRow();
            if (viewRow < 0) {
                return;
            }
            int modelRow = table.convertRowIndexToModel(viewRow);
            Object name = model.getValueAt(modelRow, 1);
            Object age = model.getValueAt(modelRow, 2);

            nameField.setText(name == null ? "" : name.toString());
            ageField.setText(age == null ? "" : age.toString());
        });

        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
