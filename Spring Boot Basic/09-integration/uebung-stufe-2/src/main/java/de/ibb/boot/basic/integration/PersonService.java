package de.ibb.boot.basic.integration;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    public List<String> names() {
        return List.of("Ada");
    }
}
