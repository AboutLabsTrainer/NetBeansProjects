/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.simple;

/**
 *
 * @author Trainer
 */
public class ReferenceTypes {
    
    public static void main(String[] args) {
        
        // primitiver Datentyp
        int number = 0;
        
        // Referenztyp / Wrapper-Klasse
        String numberAsString = "2";
        
        // AUTBOXING:
        // primitiver int-Wert wird automatisch in ein Integer-Objekt verpackt
        Integer numberAsInteger = number;
        
        Integer result;
        
        // Systemproperty lesen:
        // result = Integer.getInteger(numberAsString);
        
        // String in Zahl umwandeln:
        result = Integer.valueOf(numberAsString);
        
        System.out.println("String in Integer umgewandelt: " + result);
        
        // -------------------------------------------------
        // Beispiel 1: Autoboxing
        // -------------------------------------------------
        
        int a = 10;
        
        // Java macht intern ungefähr:
        // Integer b = Integer.valueOf(a);
        Integer b = a;
        
        System.out.println("Autoboxing:");
        System.out.println("int a = " + a);
        System.out.println("Integer b = " + b);
        
        // -------------------------------------------------
        // Beispiel 2: Unboxing
        // -------------------------------------------------
        
        Integer x = 25;
        
        // Java macht intern ungefähr:
        // int y = x.intValue();
        int y = x;
        
        System.out.println();
        System.out.println("Unboxing:");
        System.out.println("Integer x = " + x);
        System.out.println("int y = " + y);
        
        // -------------------------------------------------
        // Beispiel 3: Rechnen mit Wrapper-Typen
        // -------------------------------------------------
        
        Integer price = 100;   // Autoboxing
        Integer tax = 19;      // Autoboxing
        
        // Für die Addition müssen die Werte als primitive int-Werte verwendet werden.
        // Das passiert hier automatisch durch Unboxing.
        int total = price + tax;
        
        System.out.println();
        System.out.println("Rechnen mit Integer:");
        System.out.println("price = " + price);
        System.out.println("tax = " + tax);
        System.out.println("total = " + total);
        
        // -------------------------------------------------
        // Beispiel 4: Explizit statt automatisch
        // -------------------------------------------------
        
        int p = 7;
        
        // explizites Boxing
        Integer q = Integer.valueOf(p);
        
        // explizites Unboxing
        int r = q.intValue();
        
        System.out.println();
        System.out.println("Explizites Boxing und Unboxing:");
        System.out.println("p = " + p);
        System.out.println("q = " + q);
        System.out.println("r = " + r);
        
        // -------------------------------------------------
        // Beispiel 5: Hinweis auf mögliche NullPointerException
        // -------------------------------------------------
        
        Integer z = null;
        
        System.out.println();
        System.out.println("Hinweis:");
        System.out.println("Integer z = null;");
        System.out.println("Beim Unboxing von null würde eine NullPointerException entstehen.");
        
        // Achtung: Diese Zeile würde zur Laufzeit einen Fehler erzeugen:
        // int n = z;
    }
}