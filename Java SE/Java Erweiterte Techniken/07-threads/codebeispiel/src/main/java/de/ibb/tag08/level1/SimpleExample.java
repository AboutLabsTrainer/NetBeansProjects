package de.ibb.tag08.level1;

/**
 * Beispielklasse, die mit {@link Author} annotiert ist.
 */
@Author(name = "Kursteilnehmer", date = "2026-01-20")
public class SimpleExample {

    @Author(name = "Kursteilnehmer", date = "2026-01-20")
    public void doSomething() {
        System.out.println("SimpleExample.doSomething() wird ausgeführt.");
    }
}

