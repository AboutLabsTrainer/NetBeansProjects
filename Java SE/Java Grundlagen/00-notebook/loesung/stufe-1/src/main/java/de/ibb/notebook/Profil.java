package de.ibb.notebook;

public class Profil {
    private final String name;
    private final int alter;

    public Profil(String name, int alter) {
        this.name = name;
        this.alter = alter;
    }

    public String beschreibung() {
        return "Name: " + name + ", Alter: " + alter;
    }

    public static double kreisUmfang(double radius) {
        final double pi = 3.14159;
        return 2 * pi * radius;
    }
}
