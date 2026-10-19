package de.ibb.boot.ak.cache;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/api/prices/{sku}")
    public int price(@PathVariable String sku) {
        return priceService.price(sku);
    }

    @GetMapping("/api/taxes/{sku}")
    public int tax(@PathVariable String sku) {
        return priceService.tax(sku);
    }

}
