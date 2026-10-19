package de.ibb.swingfarbmischer;

import javax.swing.SwingUtilities;

/**
 * Startklasse: erzeugt den Farbmischer im Event-Dispatch-Thread.
 */
public class SwingFarbMischer {

    public static void main(String[] args) {
        // GUI-Erzeugung im Event-Dispatch-Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FarbMischerFrame();
            }
        });
    }
}