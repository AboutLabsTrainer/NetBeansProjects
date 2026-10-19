package de.ibb.scopes.controller;

import de.ibb.scopes.service.ApplicationScopedBean;
import de.ibb.scopes.service.PrototypeService;
import de.ibb.scopes.service.RequestScopedBean;
import de.ibb.scopes.service.SessionScopedBean;
import de.ibb.scopes.service.SingletonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Zeigt die fünf Scopes in einer Thymeleaf-Seite.
 * Der Controller selbst ist ein Singleton. Request- und Session-Beans erreicht er über einen Scoped Proxy, der beim Aufruf die richtige Instanz einsetzt.
 */
@Controller
@RequiredArgsConstructor
public class ScopeController {

    private final SingletonService singletonService;
    private final PrototypeService prototypeService;
    private final RequestScopedBean requestScopedBean;
    private final SessionScopedBean sessionScopedBean;
    private final ApplicationScopedBean applicationScopedBean;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/scope")
    public String scopeDemo(Model model) {
        singletonService.increment();
        prototypeService.increment();
        requestScopedBean.increment();
        sessionScopedBean.increment();
        applicationScopedBean.increment();

        addScopeValues(model);
        return "scope-demo";
    }

    @GetMapping("/comparison")
    public String comparison(Model model) {
        addScopeValues(model);
        return "comparison";
    }

    private void addScopeValues(Model model) {
        model.addAttribute("singletonValue", singletonService.getValue());
        model.addAttribute("prototypeValue", prototypeService.getValue());
        model.addAttribute("requestValue", requestScopedBean.getValue());
        model.addAttribute("sessionValue", sessionScopedBean.getValue());
        model.addAttribute("applicationValue", applicationScopedBean.getValue());
    }
}

