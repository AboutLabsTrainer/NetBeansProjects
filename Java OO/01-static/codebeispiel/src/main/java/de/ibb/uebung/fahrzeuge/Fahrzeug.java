package de.ibb.uebung.fahrzeuge;

/**
 * Einfache Fahrzeug-Klasse mit statischen Attributen und Methoden.
 *
 * Zeigt:
 *  - Instanzattribute (kennzeichen, baujahr, mietpreisProTag)
 *  - statische Attribute (anzahlFahrzeuge)
 *  - Konstanten (static final)
 *  - Instanzmethoden vs. statische Methoden
 */
public class Fahrzeug {

    // Instanzattribute (jedes Objekt hat eigene Werte)
    private String kennzeichen;
    private int baujahr;
    private double mietpreisProTag;
    private boolean vermietet;

    // Statisches Attribut: wird von allen Objekten gemeinsam benutzt
    private static int anzahlFahrzeuge = 0;
    private static int anzahlVermietet = 0;

    // Statische Konstanten (Konfigurationswerte)
    public static final int MAX_FAHRZEUGE = 1000;
    public static final double MWST = 0.19;

    /**
     * Konstruktor: legt ein neues Fahrzeug an und erhöht den statischen Zähler.
     */
    public Fahrzeug(String kennzeichen, int baujahr, double mietpreisProTag) {
        this.kennzeichen = kennzeichen;
        this.baujahr = baujahr;
        this.mietpreisProTag = mietpreisProTag;
        this.vermietet = false; // Standardmäßig nicht vermietet

        // Bei jedem neu erzeugten Fahrzeug wird der Zähler erhöht.
        anzahlFahrzeuge++;
    }

    // ---------- Getter / Setter (Instanzbezogen) ----------

    public String getKennzeichen() {
        return kennzeichen;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public double getMietpreisProTag() {
        return mietpreisProTag;
    }

    public void setMietpreisProTag(double mietpreisProTag) {
        this.mietpreisProTag = mietpreisProTag;
    }

    public boolean isVermietet() {
        return vermietet;
    }

    // ---------- Instanzmethoden ----------

    /**
     * Gibt die Daten dieses konkreten Fahrzeugs aus.
     */
    public void druckeDaten() {
        System.out.println("Fahrzeug " + kennzeichen);
        System.out.println("  Baujahr:        " + baujahr);
        System.out.println("  Mietpreis/Tag:  " + mietpreisProTag + " EUR netto");
        System.out.println("  Vermietet:      " + (vermietet ? "ja" : "nein"));
    }

    /**
     * Berechnet den Nettomietpreis auf Basis des Objekt-Tarifs.
     */
    public double berechneMietpreisNetto(int tage) {
        if (tage <= 0) {
            return 0.0;
        }
        return mietpreisProTag * tage;
    }

    /**
     * Berechnet den Bruttomietpreis dieses Fahrzeugs (mit MWSt),
     * basierend auf dem im Objekt gespeicherten Tagespreis.
     */
    public double berechneMietpreisBrutto(int tage) {
        double netto = berechneMietpreisNetto(tage);
        return netto * (1 + MWST);
    }

    /**
     * Vermietet dieses Fahrzeug.
     * Setzt vermietet auf true und erhöht den statischen Zähler.
     */
    public void miete() {
        if (!vermietet) {
            vermietet = true;
            anzahlVermietet++;
        }
    }

    /**
     * Gibt dieses Fahrzeug zurück.
     * Setzt vermietet auf false und verringert den statischen Zähler.
     */
    public void rueckgabe() {
        if (vermietet) {
            vermietet = false;
            anzahlVermietet--;
        }
    }

    @Override
    public String toString() {
        return "Fahrzeug{" +
                "kennzeichen='" + kennzeichen + '\'' +
                ", baujahr=" + baujahr +
                ", mietpreisProTag=" + mietpreisProTag +
                ", vermietet=" + vermietet +
                '}';
    }

    // ---------- Statische Methoden (klassenbezogen) ----------

    /**
     * Liefert die Anzahl aller bisher erzeugten Fahrzeug-Objekte.
     */
    public static int getAnzahlFahrzeuge() {
        return anzahlFahrzeuge;
    }

    /**
     * Liefert die Anzahl der aktuell vermieteten Fahrzeuge.
     */
    public static int getAnzahlVermietet() {
        return anzahlVermietet;
    }

    /**
     * Berechnet einen Bruttomietpreis OHNE konkretes Fahrzeugobjekt.
     * Nur auf Basis der übergebenen Parameter.
     *
     * Zeigt:
     *  - statische Methode, die keine Instanzattribute verwendet.
     */
    public static double berechneMietpreisBrutto(double preisProTag, int tage) {
        if (tage <= 0) {
            return 0.0;
        }
        double netto = preisProTag * tage;
        return netto * (1 + MWST);
    }

    /**
     * Gibt eine kleine Statistik über alle Fahrzeuge aus.
     */
    public static void druckeStatistik() {
        System.out.println("=== Fahrzeug-Statistik ===");
        System.out.println("Anzahl Fahrzeuge gesamt: " + anzahlFahrzeuge);
        System.out.println("Anzahl vermietet:        " + anzahlVermietet);
        System.out.println("Maximal erlaubt:         " + MAX_FAHRZEUGE);
        System.out.println("MWSt-Satz (Konstante):   " + (MWST * 100) + " %");
    }
}