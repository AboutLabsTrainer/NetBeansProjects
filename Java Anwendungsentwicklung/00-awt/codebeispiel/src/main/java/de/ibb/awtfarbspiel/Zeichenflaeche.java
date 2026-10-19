package de.ibb.awtfarbspiel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Zeichenfläche: merkt sich Mausklicks und zeichnet die Punkte zusammen mit ein paar Grundformen.
 */
public class Zeichenflaeche extends Canvas {

    private Color hintergrundfarbe = Color.LIGHT_GRAY;
    private final List<Point> punkte = new ArrayList<>();

    public Zeichenflaeche() {
        // Start-Hintergrund
        setBackground(Color.WHITE);

        // Maus-Klicks erfassen
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                punkte.add(e.getPoint());
                repaint(); // neu zeichnen
            }
        });
    }

    public void setHintergrundfarbe(Color neueFarbe) {
        this.hintergrundfarbe = neueFarbe;
        repaint();
    }

    public void loeschen() {
        punkte.clear();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        // Hintergrundfläche
        g.setColor(hintergrundfarbe);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Beispiel-Formen
        g.setColor(Color.BLACK);
        g.drawRect(20, 40, 150, 80);

        g.setColor(Color.BLUE);
        g.fillOval(200, 60, 100, 100);

        g.setColor(Color.RED);
        g.drawLine(50, 200, 250, 250);

        g.setColor(Color.WHITE);
        g.drawString("AWT", 30, 60);

        // Alle gesetzten Punkte zeichnen
        g.setColor(Color.DARK_GRAY);
        int radius = 6;
        for (Point p : punkte) {
            g.fillOval(p.x - radius / 2, p.y - radius / 2, radius, radius);
        }
    }
}