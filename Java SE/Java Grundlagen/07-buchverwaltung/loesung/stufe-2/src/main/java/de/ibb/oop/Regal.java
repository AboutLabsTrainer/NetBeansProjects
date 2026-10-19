package de.ibb.oop;
import java.util.ArrayList;
import java.util.List;
public class Regal {
    private final List<Buch> buecher = new ArrayList<>();
    public void hinzufuegen(Buch buch) {
        buecher.add(buch);
    }
    public List<Buch> vonAutor(String autor) {
        List<Buch> treffer = new ArrayList<>();
        for (Buch buch : buecher) {
            if (buch.getAutor().equals(autor)) {
                treffer.add(buch);
            }
        }
        return treffer;
    }
}
