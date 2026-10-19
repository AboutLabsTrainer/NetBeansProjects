package de.ibb.uebung.interfaces;

public interface Mietbar {

    double BASIS_MWST = 0.19;

    double berechneMietpreis(int tage);

    String getBeschreibung();

    default double berechneMietpreisMitMwst(int tage) {
        return berechneMietpreis(tage) * (1 + BASIS_MWST);
    }
}
