package de.ibb.boot.basic.scopes;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScopeController {
    private final ApplicationContext context;
    private final SingletonCounter singletonCounter;
    private final RequestCounter requestCounter;

    public ScopeController(ApplicationContext context, SingletonCounter singletonCounter, RequestCounter requestCounter) {
        this.context = context;
        this.singletonCounter = singletonCounter;
        this.requestCounter = requestCounter;
    }

    @GetMapping("/api/scopes")
    public String scopes() {
        int singleton = singletonCounter.next();
        int prototype = context.getBean(PrototypeCounter.class).next();
        return "singleton=" + singleton + ",prototype=" + prototype + ",request=" + requestCounter.next();
    }
}
