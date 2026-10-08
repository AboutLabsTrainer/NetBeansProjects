package de.ibb.streams;

/**
 * Datenklasse für Personen - wird für Level 2 Challenges benötigt.
 */
public class Person {
    private String name;
    private int alter;
    private String abteilung;

    public Person(String name, int alter, String abteilung) {
        this.name = name;
        this.alter = alter;
        this.abteilung = abteilung;
    }

    public String getName() {
        return name;
    }

    public int getAlter() {
        return alter;
    }

    public String getAbteilung() {
        return abteilung;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', alter=" + alter + ", abteilung='" + abteilung + "'}";
    }
}
