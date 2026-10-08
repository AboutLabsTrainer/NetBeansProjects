package de.ibb.fenstertrainer;

import javax.swing.SwingUtilities;

/**
 * Startklasse des Fenster-Trainers. Die Oberfläche entsteht im Event-Dispatch-Thread.
 */
public class FensterTrainer {

    public static void main(String[] args) {
        // GUI immer im Event-Dispatch-Thread starten
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FensterTrainerFrame();
            }
        });
    }
}