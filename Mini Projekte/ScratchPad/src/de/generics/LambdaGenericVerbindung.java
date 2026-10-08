/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.generics;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Verbindet Lambdas ({@code Supplier}, {@code Consumer}) mit den Grenzen {@code extends} und {@code super}.
 */
public class LambdaGenericVerbindung {

    // Dieselbe Hierarchie wie im Recycling-Beispiel, aber lokal, damit beide Dateien zusammen übersetzt werden.
    static class Gegenstand {}
    static class Plastikmuell extends Gegenstand {}
    static class PetFlasche extends Plastikmuell {}

    public static void main(String[] args) {
        
        // --- 1. Lambda-Beispiele für PRODUCER (Supplier) ---
        // Erlaubt: Liefert genau Plastikmüll
        Supplier<Plastikmuell> plastikFabrik = () -> new Plastikmuell();
        muellProduzieren(plastikFabrik);

        // Erlaubt dank '? extends': Liefert eine PET-Flasche (eine Unterklasse)
        Supplier<PetFlasche> flaschenFabrik = () -> new PetFlasche();
        muellProduzieren(flaschenFabrik);


        // --- 2. Lambda-Beispiele für CONSUMER (Consumer) ---
        Plastikmuell eineTüte = new Plastikmuell();

        // Erlaubt: Verarbeitet genau Plastikmüll
        Consumer<Plastikmuell> plastikVerwerter = m -> System.out.println("Recycle Plastik");
        muellVerarbeiten(eineTüte, plastikVerwerter);

        // Erlaubt dank '? super': Ein allgemeiner Verarbeiter, der jeden Gegenstand (Oberklasse) nimmt
        Consumer<Gegenstand> allgemeinerVerwerter = g -> System.out.println("Verbrenne allgemeinen Gegenstand");
        muellVerarbeiten(eineTüte, allgemeinerVerwerter);
    }

    // PRODUCER (? extends T) -> Nutzt hier einen Supplier
    // Diese Methode akzeptiert jeden Erzeuger, der Plastikmüll ODER eine Unterklasse liefert.
    public static void muellProduzieren(Supplier<? extends Plastikmuell> hersteller) {
        Plastikmuell muell = hersteller.get(); // Sicher: Es kommt garantiert mindestens Plastikmüll heraus
        System.out.println("Produziert: " + muell.getClass().getSimpleName());
    }

    // CONSUMER (? super T) -> Nutzt hier einen Consumer
    // Diese Methode akzeptiert jeden Verbraucher, der Plastikmüll ODER eine Oberklasse verarbeiten kann.
    public static void muellVerarbeiten(Plastikmuell muell, Consumer<? super Plastikmuell> verarbeiter) {
        verarbeiter.accept(muell); // Sicher: Der Verarbeiter kommt mit Plastikmüll (und dessen Elternklassen) klar
    }

}

