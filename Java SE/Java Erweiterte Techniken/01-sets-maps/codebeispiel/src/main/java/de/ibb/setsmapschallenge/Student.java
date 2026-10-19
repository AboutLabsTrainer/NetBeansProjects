package de.ibb.setsmapschallenge;

import java.util.Objects;

/**
 * Student-Klasse für Level 2, Aufgabe 1.
 * Demonstriert korrekte Implementierung von equals() und hashCode()
 * für die Verwendung in Sets und Maps.
 */
public class Student {
    private String matrikelnummer;
    private String name;
    private int alter;

    public Student(String matrikelnummer, String name, int alter) {
        this.matrikelnummer = matrikelnummer;
        this.name = name;
        this.alter = alter;
    }

    public String getMatrikelnummer() {
        return matrikelnummer;
    }

    public String getName() {
        return name;
    }

    public int getAlter() {
        return alter;
    }

    /**
     * Überschreibt equals() gemäß dem equals/hashCode-Vertrag.
     * Zwei Student-Objekte sind gleich, wenn Matrikelnummer und Name übereinstimmen.
     */
    @Override
    public boolean equals(Object o) {
        // 1. Identität: Bin ich dasselbe Objekt?
        if (this == o) return true;
        
        // 2. Null-Check und Typ-Check
        if (o == null || getClass() != o.getClass()) return false;
        
        // 3. Cast und Feldvergleich
        Student student = (Student) o;
        return Objects.equals(matrikelnummer, student.matrikelnummer)
                && Objects.equals(name, student.name);
    }

    /**
     * Überschreibt hashCode() gemäß dem equals/hashCode-Vertrag.
     * WICHTIG: Muss dieselben Felder verwenden wie equals()!
     */
    @Override
    public int hashCode() {
        return Objects.hash(matrikelnummer, name);
    }

    @Override
    public String toString() {
        return "Student{matrikelnummer='" + matrikelnummer + "', name='" + name + "', alter=" + alter + "}";
    }
}
