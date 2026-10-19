package de.ibb.workshop;
public class NamensApp {
    public static void main(String[] args) {
        System.out.println(NamensTeile.vorname("Max Mustermann"));
        System.out.println(NamensTeile.nachname("Max Mustermann"));
        System.out.println(NamensTeile.enthaelt("Java macht Spaß", "Java"));
    }
}
