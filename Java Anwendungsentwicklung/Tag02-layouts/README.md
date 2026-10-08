# Fenster-Trainer

## Was dieses Beispiel übt

Ein Swing-Fenster mit BorderLayout und FlowLayout, Mausklicks auf einer Fläche, Pfeiltasten und einfache Dialoge (`JOptionPane`).

## Klassen

In dieser Reihenfolge lesen:

1. `FensterTrainer` – Start, Oberfläche im Event-Dispatch-Thread
2. `FensterTrainerFrame` – Fenster, Statuszeile, Buttons
3. `ArbeitsflaechePanel` – Klicks, bewegliches Label, Zeichnen der Punkte

## Start in NetBeans

F6 startet `fenstertrainer.FensterTrainer` (`main`). Es öffnet sich ein Fenster, kein Server.

## Ausprobieren

1. In die Arbeitsfläche klicken. Die Punkte bleiben stehen, die Statuszeile nennt die Position.
2. Die Fläche anklicken und das Label mit den Pfeiltasten verschieben.
3. „Info“ und „Eingabe“ öffnen Dialoge. „Beenden“ schließt das Fenster.
