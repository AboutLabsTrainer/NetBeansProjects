package de.ibb.fenstertrainer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Fläche für Mausklicks und ein Label, das sich mit den Pfeiltasten verschieben lässt.
 */
public class ArbeitsflaechePanel extends JPanel {

    private final FensterTrainerFrame hauptfenster;
    private final List<Point> klickPunkte = new ArrayList<>();
    private final JLabel beweglichesLabel;

    public ArbeitsflaechePanel(FensterTrainerFrame hauptfenster) {
        this.hauptfenster = hauptfenster;

        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Arbeitsfläche"));
        setLayout(null); // absolute Positionierung für das Label

        // Label, das mit den Pfeiltasten bewegt werden kann
        beweglichesLabel = new JLabel("Bewege mich mit den Pfeiltasten");
        beweglichesLabel.setSize(beweglichesLabel.getPreferredSize());
        beweglichesLabel.setLocation(50, 50);
        add(beweglichesLabel);

        // Maus-Listener: Klickposition speichern + Status updaten
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                klickPunkte.add(e.getPoint());
                hauptfenster.setStatus("Mausklick bei (" + e.getX() + ", " + e.getY() + ")");
                repaint();
                // Fokus auf dieses Panel holen (für Tastatur)
                requestFocusInWindow();
            }
        });

        // Tastatur-Listener: Label mit Pfeiltasten bewegen
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int schritt = 5;
                Point pos = beweglichesLabel.getLocation();

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        pos.x -= schritt;
                        hauptfenster.setStatus("Letzte Taste: Pfeil links");
                        break;
                    case KeyEvent.VK_RIGHT:
                        pos.x += schritt;
                        hauptfenster.setStatus("Letzte Taste: Pfeil rechts");
                        break;
                    case KeyEvent.VK_UP:
                        pos.y -= schritt;
                        hauptfenster.setStatus("Letzte Taste: Pfeil hoch");
                        break;
                    case KeyEvent.VK_DOWN:
                        pos.y += schritt;
                        hauptfenster.setStatus("Letzte Taste: Pfeil runter");
                        break;
                    default:
                        // andere Tasten ignorieren
                        return;
                }

                beweglichesLabel.setLocation(pos);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Alle gemerkten Klick-Punkte als kleine Kreise zeichnen
        g.setColor(Color.RED);
        int radius = 8;
        for (Point p : klickPunkte) {
            g.fillOval(p.x - radius / 2, p.y - radius / 2, radius, radius);
        }
    }
}