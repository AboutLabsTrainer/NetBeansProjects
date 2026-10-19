package de.ibb.greeting;

public class GreetingService {
    private final GreetingProperties properties;

    public GreetingService(GreetingProperties properties) {
        this.properties = properties;
    }

    public String greet(String name) {
        // TODO: message, Leerzeichen, name und suffix zusammensetzen
        return name;
    }
}
