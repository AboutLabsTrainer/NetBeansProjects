package de.ibb.boot.basic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {
    private static final Logger log = LoggerFactory.getLogger(ConfigController.class);
    private final LabProperties properties;

    public ConfigController(LabProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/api/config")
    public String title() {
        log.debug("title={}", properties.getTitle());
        return properties.getTitle();
    }

    @GetMapping("/api/config/subtitle")
    public String subtitle() {
        // TODO: return properties.getSubtitle();
        return "";
    }

}
