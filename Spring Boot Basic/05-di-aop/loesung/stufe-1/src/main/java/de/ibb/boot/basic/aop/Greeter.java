package de.ibb.boot.basic.aop;

import org.springframework.stereotype.Service;

@Service
public class Greeter {
    @Counted
    public String hello(String name) {
        return "Hallo " + name;
    }

}
