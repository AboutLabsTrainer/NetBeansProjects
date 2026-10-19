package de.ibb.boot.ak.messaging;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Component;

@Component
public class OrderQueues {
    private final Map<String, List<String>> data = new ConcurrentHashMap<>();

    public void publish(String queue, String body) {
        // TODO: body an die Liste der queue haengen

    }

    public List<String> snapshot(String queue) {
        return List.copyOf(data.getOrDefault(queue, List.of()));
    }
}
