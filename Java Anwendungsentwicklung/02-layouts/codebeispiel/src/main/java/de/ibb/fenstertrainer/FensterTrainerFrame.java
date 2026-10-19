package de.ibb.fenstertrainer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Hauptfenster: Hinweistext, Arbeitsfläche, Statuszeile und die Dialog-Buttons.
 */
public class FensterTrainerFrame extends JFrame {

    private JLabel statusLabel;
    private ArbeitsflaechePanel arbeitsflaechePanel;

    public FensterTrainerFrame() {
        super("Fenster-Trainer");

        // Grundkonfiguration des Fensters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Hinweis-Text oben
        JLabel infoLabel = new JLabel(
                "Klicken Sie in die Arbeitsfläche und bewegen Sie das Label mit den Pfeiltasten."
        );
        add(infoLabel, BorderLayout.NORTH);

        // Arbeitsfläche in der Mitte
        arbeitsflaechePanel = new ArbeitsflaechePanel(this);
        add(arbeitsflaechePanel, BorderLayout.CENTER);

        // Statuszeile + Buttons unten
        JPanel untenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        statusLabel = new JLabel("Bereit");
        JButton btnInfo = new JButton("Info");
        JButton btnEingabe = new JButton("Eingabe");
        JButton btnBeenden = new JButton("Beenden");

        untenPanel.add(statusLabel);
        untenPanel.add(btnInfo);
        untenPanel.add(btnEingabe);
        untenPanel.add(btnBeenden);

        add(untenPanel, BorderLayout.SOUTH);

        // Aktionen für die Buttons
        btnInfo.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Dies ist ein Informationsdialog.\n" +
                    "Das Hauptfenster bleibt im Hintergrund geöffnet.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        btnEingabe.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(
                    this,
                    "Bitte geben Sie Ihren Namen ein:",
                    "Eingabe",
                    JOptionPane.QUESTION_MESSAGE
            );
            if (name != null && !name.trim().isEmpty()) {
                setStatus("Letzte Eingabe: " + name.trim());
            } else if (name != null) {
                setStatus("Leere Eingabe.");
            }
        });

        btnBeenden.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Programm wirklich beenden?",
                    "Bestätigung",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
                System.exit(0);
            }
        });

        pack();
        setLocationRelativeTo(null); // Fenster zentrieren
        setVisible(true);

        // Fokus auf die Arbeitsfläche setzen, damit Tastatureingaben ankommen
        arbeitsflaechePanel.requestFocusInWindow();
    }

    // Wird von der Arbeitsfläche genutzt, um unten den Status zu ändern
    public void setStatus(String text) {
        statusLabel.setText(text);
    }
}