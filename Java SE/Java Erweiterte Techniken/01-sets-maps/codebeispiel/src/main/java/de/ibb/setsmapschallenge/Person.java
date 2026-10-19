package de.ibb.setsmapschallenge;

/**
 * Person-Klasse für Level 3, Aufgabe 2.
 * Wird in einem TreeSet mit Comparator verwendet (Sortierung: erst Alter, dann Name).
 */
public class Person {
    private String vorname;
    private String nachname;
    private int alter;

    public Person(String vorname, String nachname, int alter) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.alter = alter;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public int getAlter() {
        return alter;
    }

    @Override
    public String toString() {
        return vorname + " " + nachname + " (Alter: " + alter + ")";
    }
}
