package com.example.cachingdemo;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Fassade für die Rechenoperationen und die Cache-Statistik.
 * Die Dauer in der Antwort zeigt, ob der Cache-Proxy die Methode ausgeführt oder den gespeicherten Wert zurückgegeben hat.
 */
@RestController
@RequestMapping("/calc")
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final CacheManager cacheManager;

    public CalculatorController(CalculatorService calculatorService, CacheManager cacheManager) {
        this.calculatorService = calculatorService;
        this.cacheManager = cacheManager;
    }

    @GetMapping("/add")
    public String add(@RequestParam double a, @RequestParam double b) {
        return timed(() -> calculatorService.add(a, b), "Addition", a, b);
    }

    @GetMapping("/subtract")
    public String subtract(@RequestParam double a, @RequestParam double b) {
        return timed(() -> calculatorService.subtract(a, b), "Subtraktion", a, b);
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam double a, @RequestParam double b) {
        return timed(() -> calculatorService.multiply(a, b), "Multiplikation", a, b);
    }

    @GetMapping("/divide")
    public String divide(@RequestParam double a, @RequestParam double b) {
        return timed(() -> calculatorService.divide(a, b), "Division", a, b);
    }

    @GetMapping("/power")
    public String power(@RequestParam double base, @RequestParam double exponent) {
        return timed(() -> calculatorService.power(base, exponent), "Potenz", base, exponent);
    }

    @PostMapping("/clear")
    public String clearCache() {
        calculatorService.clearAllCaches();
        return "Alle Caches wurden geleert!";
    }

    @GetMapping("/stats")
    public String getStats() {
        StringBuilder sb = new StringBuilder("=== Cache Stats ===\n\n");

        for (String name : new String[]{"additions", "subtractions", "multiplications", "divisions", "powers"}) {
            var cache = cacheManager.getCache(name);
            if (cache instanceof CaffeineCache caffeineCache) {
                var nativeCache = caffeineCache.getNativeCache();
                CacheStats stats = nativeCache.stats();
                sb.append(String.format(
                        "[%s] Hits: %d | Misses: %d | Hit Rate: %.1f%% | Size: %d%n",
                        name, stats.hitCount(), stats.missCount(),
                        stats.hitRate() * 100, nativeCache.estimatedSize()
                ));
            }
        }

        return sb.toString();
    }

    @FunctionalInterface
    private interface Calculation {
        double execute();
    }

    private String timed(Calculation calc, String label, double a, double b) {
        long start = System.currentTimeMillis();
        double result = calc.execute();
        long duration = System.currentTimeMillis() - start;
        return String.format("%s: %.2f | Dauer: %dms | Parameter: (%.1f, %.1f)", label, result, duration, a, b);
    }
}
