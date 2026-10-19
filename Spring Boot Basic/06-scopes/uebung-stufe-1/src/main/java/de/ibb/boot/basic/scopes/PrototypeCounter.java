package de.ibb.boot.basic.scopes;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Scope("prototype")

@Component
public class PrototypeCounter {
    private int value;

    public int next() {
        // TODO: return ++value;
        return 0;
    }
}
