package de.ibb.boot.ak.messaging;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderQueues queues;

    public OrderController(OrderQueues queues) {
        this.queues = queues;
    }

    @PostMapping("/api/orders")
    public List<String> publish(@RequestParam String text) {
        queues.publish("orders", text);
        return queues.snapshot("orders");
    }

    @GetMapping("/api/orders")
    public List<String> list() {
        return queues.snapshot("orders");
    }

}
