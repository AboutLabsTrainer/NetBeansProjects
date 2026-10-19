package de.ibb.boot.basic.di;

import org.springframework.stereotype.Service;

@Service("germanGreeting")
public class GermanGreeting implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hallo " + name;
    }
}
