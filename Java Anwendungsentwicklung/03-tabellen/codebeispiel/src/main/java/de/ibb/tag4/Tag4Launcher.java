package de.ibb.tag4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Optionaler Launcher für Tag 4:
 * Öffnet die drei Demos (Level 1–3) über Buttons.
 */
public class Tag4Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tag4Launcher::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Tag 4 – JTable Demos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton level1Button = new JButton("Level 1 – Einfache Tabelle");
        JButton level2Button = new JButton("Level 2 – CRUD-Tabelle");
        JButton level3Button = new JButton("Level 3 – Custom TableModel");

        level1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Level1TableDemo.main(new String[0]);
            }
        });

        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrudTableDemo.main(new String[0]);
            }
        });

        level3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomTableModelDemo.main(new String[0]);
            }
        });

        frame.add(level1Button);
        frame.add(level2Button);
        frame.add(level3Button);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
