package de.ibb.sockets;

import java.util.List;

public class ChatRaum {

    public boolean beitreten(String name) {
        // TODO false, wenn der Name schon drin ist
        return false;
    }

    public boolean verlassen(String name) {
        // TODO Name entfernen
        return false;
    }

    public List<String> beitrittsEmpfaenger(String neu) {
        // TODO alle außer neu, Beitrittsreihenfolge
        return List.of();
    }

    public List<String> nachrichtenEmpfaenger() {
        // TODO alle Namen
        return List.of();
    }

    public String zeile(String absender, String text) {
        // TODO [absender] text
        return "";
    }

    public boolean istQuit(String text) {
        // TODO /quit nach trim
        return false;
    }

    public static void main(String[] args) {
        ChatRaum raum = new ChatRaum();
        raum.beitreten("A");
        raum.beitreten("B");
        raum.beitreten("C");
        System.out.println(raum.beitrittsEmpfaenger("C"));
        System.out.println(raum.zeile("pool-1-thread-1", "Hallo"));
        System.out.println(raum.istQuit(" /quit "));
    }
}
