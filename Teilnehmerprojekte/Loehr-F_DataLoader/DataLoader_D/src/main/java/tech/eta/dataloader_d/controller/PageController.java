package tech.eta.dataloader_d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String root() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
        // return "forward:/dashboard.html";
    }

    @GetMapping("/dummy")
    public String dummy() {
        return "dummy";
    }

    // note: we could combine the endpoints, since we don't really need a redirect.
    @GetMapping("/login")
    public String login() {
        // The dashboard contains the login form.
        // => should we saparate this again? Or should we remove the endpoint? also its security?
        return "dashboard";
    }
}
