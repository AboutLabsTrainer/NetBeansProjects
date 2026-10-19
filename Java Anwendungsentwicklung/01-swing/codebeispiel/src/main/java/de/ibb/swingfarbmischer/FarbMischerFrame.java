package de.ibb.swingfarbmischer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Fenster mit RGB-Reglern, Farbvorschau und den Buttons für Zufallsfarbe und Zurücksetzen.
 */
public class FarbMischerFrame extends JFrame {

    private final JPanel vorschauPanel;
    private final JSlider rotSlider;
    private final JSlider gruenSlider;
    private final JSlider blauSlider;

    public FarbMischerFrame() {
        super("Swing Farbmischer");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Info-Text oben
        JLabel infoLabel = new JLabel(
                "Regler bewegen oder Zufallsfarbe erzeugen, um eine Farbe zu mischen."
        );
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(infoLabel, BorderLayout.NORTH);

        // Vorschau in der Mitte
        vorschauPanel = new JPanel();
        vorschauPanel.setPreferredSize(new Dimension(300, 200));
        vorschauPanel.setBackground(Color.BLACK);
        vorschauPanel.setBorder(BorderFactory.createTitledBorder("Vorschau"));
        add(vorschauPanel, BorderLayout.CENTER);

        // Steuer-Panel unten
        JPanel steuerPanel = new JPanel(new GridBagLayout());
        steuerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        rotSlider = new JSlider(0, 255, 0);
        gruenSlider = new JSlider(0, 255, 0);
        blauSlider = new JSlider(0, 255, 0);

        // Beschriftungen links
        gbc.gridx = 0;
        gbc.gridy = 0;
        steuerPanel.add(new JLabel("Rot"), gbc);
        gbc.gridy = 1;
        steuerPanel.add(new JLabel("Grün"), gbc);
        gbc.gridy = 2;
        steuerPanel.add(new JLabel("Blau"), gbc);

        // Slider rechts
        gbc.gridx = 1;
        gbc.gridy = 0;
        steuerPanel.add(rotSlider, gbc);
        gbc.gridy = 1;
        steuerPanel.add(gruenSlider, gbc);
        gbc.gridy = 2;
        steuerPanel.add(blauSlider, gbc);

        // Buttons unten
        JButton zufallsButton = new JButton("Zufallsfarbe");
        JButton resetButton = new JButton("Zurücksetzen");

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        steuerPanel.add(zufallsButton, gbc);

        gbc.gridx = 1;
        steuerPanel.add(resetButton, gbc);

        add(steuerPanel, BorderLayout.SOUTH);

        // Listener für alle Slider
        ChangeListener sliderListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateColor();
            }
        };
        rotSlider.addChangeListener(sliderListener);
        gruenSlider.addChangeListener(sliderListener);
        blauSlider.addChangeListener(sliderListener);

        // Aktionen der Buttons
        zufallsButton.addActionListener(e -> {
            rotSlider.setValue((int) (Math.random() * 256));
            gruenSlider.setValue((int) (Math.random() * 256));
            blauSlider.setValue((int) (Math.random() * 256));
        });

        resetButton.addActionListener(e -> {
            rotSlider.setValue(0);
            gruenSlider.setValue(0);
            blauSlider.setValue(0);
        });

        pack();
        setLocationRelativeTo(null); // Fenster zentrieren
        setVisible(true);
    }

    private void updateColor() {
        int r = rotSlider.getValue();
        int g = gruenSlider.getValue();
        int b = blauSlider.getValue();

        Color c = new Color(r, g, b);
        vorschauPanel.setBackground(c);
        vorschauPanel.repaint();
    }
}