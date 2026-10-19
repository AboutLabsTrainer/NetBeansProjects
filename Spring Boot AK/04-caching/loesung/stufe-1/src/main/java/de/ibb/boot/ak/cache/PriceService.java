package de.ibb.boot.ak.cache;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    private final AtomicInteger prices = new AtomicInteger();

    @Cacheable("prices")
    public int price(String sku) {
        return prices.incrementAndGet();
    }

    public int priceCalls() {
        return prices.get();
    }

}
