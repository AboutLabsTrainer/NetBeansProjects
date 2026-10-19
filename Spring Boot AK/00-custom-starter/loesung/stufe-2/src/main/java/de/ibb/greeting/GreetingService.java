package de.ibb.greeting;

public class GreetingService {
    private final GreetingProperties properties;

    public GreetingService(GreetingProperties properties) {
        this.properties = properties;
    }

    public String greet(String name) {
        String suffix = properties.getSuffix() == null ? "" : properties.getSuffix();
        return properties.getMessage() + " " + name + suffix;
    }
}
