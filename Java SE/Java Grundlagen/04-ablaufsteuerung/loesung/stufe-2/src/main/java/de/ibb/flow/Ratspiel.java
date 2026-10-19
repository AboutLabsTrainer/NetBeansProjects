package de.ibb.flow;
public class Ratspiel {
    public static String hinweis(int geheim, int tipp) {
        if (tipp < geheim) {
            return "zu klein";
        }
        if (tipp > geheim) {
            return "zu gross";
        }
        return "getroffen";
    }
}
