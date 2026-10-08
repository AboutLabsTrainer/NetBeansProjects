/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Zeigt obere und untere Typschranken ({@code ? extends} und {@code ? super}) an einer kleinen Typhierarchie.
 */
public class RecyclingBeispiel {

    // Hierarchie nur in diesem Beispiel, damit sie nicht mit LambdaGenericVerbindung kollidiert.
    static class Gegenstand {}
    static class Plastikmuell extends Gegenstand {}
    static class PetFlasche extends Plastikmuell {}

    public static void main(String[] args) {
        // Vorbereitung der verschiedenen Behälter
        List<Gegenstand> allgemeineTonne = new ArrayList<>();
        List<Plastikmuell> plastikTonne = new ArrayList<>();
        List<PetFlasche> flaschenKiste = new ArrayList<>();

        flaschenKiste.add(new PetFlasche());

        // --- Test: Upper Bound (plastikSchreddern) ---
        plastikSchreddern(plastikTonne);  // Erlaubt: Ist genau Plastikmuell
        plastikSchreddern(flaschenKiste); // Erlaubt: PetFlasche extends Plastikmuell
        // plastikSchreddern(allgemeineTonne); // FEHLER! Ein allgemeiner Gegenstand könnte den Schredder beschädigen.

        // --- Test: Lower Bound (inTonneWerfen) ---
        inTonneWerfen(plastikTonne);     // Erlaubt: Ist genau Plastikmuell
        inTonneWerfen(allgemeineTonne);  // Erlaubt: Gegenstand ist eine Oberklasse von Plastikmuell
        // inTonneWerfen(flaschenKiste); // FEHLER! In eine reine PET-Kiste darf kein unbestimmter Plastikmüll.
    }

    // 1. UPPER BOUND (? extends Plastikmuell) -> Der Schredder (KONSUMENT / LESEN)
    // Diese Methode akzeptiert Listen von Plastikmuell oder PetFlasche.
    public static void plastikSchreddern(List<? extends Plastikmuell> schredderGut) {
        for (Plastikmuell muell : schredderGut) {
            // Sicher beim LESEN: Alles hier drin ist garantiert MINDESTENS Plastikmuell.
            System.out.println("Schreddere: " + muell.getClass().getSimpleName());
        }
        
        // schredderGut.add(new PetFlasche()); // FEHLER! Java verbietet das Hinzufügen, 
        // da die Liste im Aufruf auch eine reine "PetFlaschen-Liste" sein könnte.
    }

    // 2. LOWER BOUND (? super Plastikmuell) -> Die Mülltonne (PRODUZENT / SCHREIBEN)
    // Diese Methode akzeptiert Listen von Plastikmuell oder Gegenstand.
    public static void inTonneWerfen(List<? super Plastikmuell> muellTonne) {
        // Sicher beim SCHREIBEN: Wir können garantiert Plastikmuell hineintun.
        muellTonne.add(new Plastikmuell());
        muellTonne.add(new PetFlasche()); // Geht auch, da eine PetFlasche Plastikmuell ist.

        // Object gegenstand = muellTonne.get(0); // Beim LESEN liefert Java hier nur 'Object',
        // weil wir nicht wissen, wie allgemein die Tonne im Hintergrund wirklich ist.
    }

}

