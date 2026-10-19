package de.ibb.boot.basic.scopes;

import org.springframework.stereotype.Component;


@Component
public class SingletonCounter {
    private int value;

    public int next() {
        return ++value;
    }
}
