package de.ibb.notebook;

public class ProfilApp {
    public static void main(String[] args) {
        Profil profil = new Profil("Petra", 30);
        System.out.println(profil.beschreibung());
        System.out.println(Profil.kreisUmfang(5));
    }
}
