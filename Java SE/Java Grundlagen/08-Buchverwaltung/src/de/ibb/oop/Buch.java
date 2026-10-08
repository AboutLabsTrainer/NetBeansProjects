package de.ibb.oop;

/**
 * Einfache Modellklasse für ein Buch.
 * Demonstriert:
 * - Attribute (Felder)
 * - Konstruktoren
 * - Getter/Setter
 * - Fachliche Methoden
 */
public class Buch {

    // Attribute (Kapselung: private)
    private String titel;
    private String autor;
    private int erscheinungsjahr;
    private double preis;

    // Vollständiger Konstruktor
    public Buch(String titel, String autor, int erscheinungsjahr, double preis) {
        this.titel = titel;
        this.autor = autor;
        this.erscheinungsjahr = erscheinungsjahr;
        this.preis = preis;
    }

    // Standard-Konstruktor mit Default-Werten
    public Buch() {
        this.titel = "Unbekannt";
        this.autor = "Unbekannt";
        this.erscheinungsjahr = 0;
        this.preis = 0.0;
    }

    // Getter und Setter
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public void setErscheinungsjahr(int erscheinungsjahr) {
        this.erscheinungsjahr = erscheinungsjahr;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    // Fachliche Methoden

    /**
     * Gibt alle Informationen zum Buch auf der Konsole aus.
     */
    public void druckeInfo() {
        System.out.printf(
            "Titel: %s, Autor: %s, Jahr: %d, Preis: %.2f EUR%n",
            titel, autor, erscheinungsjahr, preis
        );
    }

    /**
     * Reduziert den Preis um den angegebenen Prozentsatz.
     * Beispiel: rabatt(10) = 10 % Rabatt.
     *
     * @param prozent Rabatt in Prozent (z.B. 10.0 für 10 %)
     */
    public void rabatt(double prozent) {
        double faktor = 1 - (prozent / 100.0);
        this.preis = this.preis * faktor;
    }
}
