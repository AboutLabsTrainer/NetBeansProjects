package de.ibb.sockets;

import java.util.ArrayList;
import java.util.List;

public class ChatRaum {

    private final List<String> namen = new ArrayList<>();

    public boolean beitreten(String name) {
        if (namen.contains(name)) {
            return false;
        }
        namen.add(name);
        return true;
    }

    public boolean verlassen(String name) {
        return namen.remove(name);
    }

    public List<String> beitrittsEmpfaenger(String neu) {
        return namen.stream().filter(name -> !name.equals(neu)).toList();
    }

    public List<String> nachrichtenEmpfaenger() {
        return List.copyOf(namen);
    }

    public String zeile(String absender, String text) {
        return "[" + absender + "] " + text;
    }

    public boolean istQuit(String text) {
        return text != null && "/quit".equalsIgnoreCase(text.trim());
    }

    public static void main(String[] args) {
        ChatRaum raum = new ChatRaum();
        raum.beitreten("A");
        raum.beitreten("B");
        raum.beitreten("C");
        System.out.println(raum.beitrittsEmpfaenger("C"));
        System.out.println(raum.nachrichtenEmpfaenger());
        System.out.println(raum.zeile("pool-1-thread-1", "Hallo"));
    }
}
