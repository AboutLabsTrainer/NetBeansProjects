package de.ibb.boot.basic.di;

import org.springframework.stereotype.Service;

@Service("englishGreeting")
public class EnglishGreeting implements GreetingService {
    @Override
    public String greet(String name) {
        // TODO: return "Hello " + name;
        return name;
    }
}
