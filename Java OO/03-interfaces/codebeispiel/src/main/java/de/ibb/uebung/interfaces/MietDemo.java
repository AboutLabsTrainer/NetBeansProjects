package de.ibb.uebung.interfaces;

/**
 * Demo- und Testklasse für das Projekt "InterfacesMietobjekte".
 */
public class MietDemo {

    public static void main(String[] args) {

        // Mietobjekte anlegen
        Auto auto = new Auto("B-AB 1234", 59.99);
        Fahrrad fahrrad = new Fahrrad("R-001", false);
        Fahrrad eBike = new Fahrrad("E-007", true);
        Bohrmaschine bohrer = new Bohrmaschine("ProDrill 500", 1200);

        // Polymorphes Array über das Interface Mietbar
        Mietbar[] mietobjekte = { auto, fahrrad, eBike, bohrer };

        int tage = 3;

        System.out.println("=== Mietobjekte (Netto & Brutto) ===");
        for (Mietbar m : mietobjekte) {
            double netto = m.berechneMietpreis(tage);
            double brutto = m.berechneMietpreisMitMwst(tage);

            System.out.printf("%s%n", m.getBeschreibung());
            System.out.printf("  Mietdauer: %d Tage%n", tage);
            System.out.printf("  Preis netto:  %.2f EUR%n", netto);
            System.out.printf("  Preis brutto: %.2f EUR%n", brutto);
            System.out.println();
        }

        // Versicherbare Objekte
        System.out.println("=== Versicherungskosten ===");
        druckeVersicherungskosten(auto);
        druckeVersicherungskosten(bohrer);
        System.out.println();

        // Saison- & Rabatt-Demo
        System.out.println("=== Saison & Rabatt-Demo ===");
        Saison saison = Saison.HAUPTSAISON;
        int saisonTage = 7;

        double nettoAuto = auto.berechneMietpreis(saisonTage);
        double faktor = saison.getFaktor();
        double rabattProzent = saison.berechneRabattProzent(saisonTage);

        double nettoMitSaison = nettoAuto * faktor;
        double rabattBetrag = nettoMitSaison * (rabattProzent / 100.0);
        double endpreis = nettoMitSaison - rabattBetrag;

        System.out.printf("Saison: %s%n", saison);
        System.out.printf("Auto: %s%n", auto.getBeschreibung());
        System.out.printf("Tage: %d%n", saisonTage);
        System.out.printf("Netto ohne Saison:      %.2f EUR%n", nettoAuto);
        System.out.printf("Saisonfaktor:           %.2f%n", faktor);
        System.out.printf("Netto mit Saison:       %.2f EUR%n", nettoMitSaison);
        System.out.printf("Rabatt:                 %.2f %% (%.2f EUR)%n", rabattProzent, rabattBetrag);
        System.out.printf("Endpreis (netto):       %.2f EUR%n", endpreis);
    }

    public static void druckeVersicherungskosten(Versicherbar v) {
        System.out.printf("Versicherungsprämie für %s: %.2f EUR%n",
                beschreibeVersicherbaresObjekt(v),
                v.berechneVersicherungsPraemie());
    }

    private static String beschreibeVersicherbaresObjekt(Versicherbar v) {
        if (v instanceof Auto) {
            return ((Auto) v).getBeschreibung();
        } else if (v instanceof Bohrmaschine) {
            return ((Bohrmaschine) v).getBeschreibung();
        } else {
            return v.toString();
        }
    }
}