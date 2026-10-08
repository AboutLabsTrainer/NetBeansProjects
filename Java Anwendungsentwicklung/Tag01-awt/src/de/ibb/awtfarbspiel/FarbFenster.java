package de.ibb.awtfarbspiel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * AWT-Fenster mit Hinweis, Zeichenfläche und den Buttons zum Wechseln der Farbe und zum Löschen.
 */
public class FarbFenster extends Frame {

    private Zeichenflaeche zeichenflaeche;

    public FarbFenster() {
        super("AWT Farbspiel");

        // Layout für das Fenster setzen
        setLayout(new BorderLayout());

        // Info-Text oben
        Label infoLabel = new Label(
                "Klicken Sie in die Fläche, um Punkte zu setzen. " +
                "Buttons ändern die Hintergrundfarbe bzw. löschen alle Punkte."
        );
        add(infoLabel, BorderLayout.NORTH);

        // Zeichenfläche in der Mitte
        zeichenflaeche = new Zeichenflaeche();
        add(zeichenflaeche, BorderLayout.CENTER);

        // Button-Leiste unten
        Panel buttonPanel = new Panel();
        Button farbeWechselnButton = new Button("Farbe wechseln");
        Button loeschenButton = new Button("Löschen");
        buttonPanel.add(farbeWechselnButton);
        buttonPanel.add(loeschenButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Aktionen für die Buttons
        farbeWechselnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color randomColor = new Color(
                        (int) (Math.random() * 256),
                        (int) (Math.random() * 256),
                        (int) (Math.random() * 256)
                );
                zeichenflaeche.setHintergrundfarbe(randomColor);
            }
        });

        loeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zeichenflaeche.loeschen();
            }
        });

        // Fenster korrekt schließen
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        // Fenstergröße und Position
        setSize(800, 600);
        setLocationRelativeTo(null); // zentriert das Fenster auf dem Bildschirm
        setVisible(true);
    }
}