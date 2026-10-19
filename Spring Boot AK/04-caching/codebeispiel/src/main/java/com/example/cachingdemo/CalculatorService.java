package com.example.cachingdemo;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Rechnet langsam und legt Ergebnisse im Cache ab.
 * Der Cache-Proxy speichert den Rückgabewert zum Aufrufschlüssel. clearAllCaches leert alle Regionen, der nächste Aufruf rechnet neu.
 */
@Service
public class CalculatorService {

    @Cacheable("additions")
    public double add(double a, double b) {
        simulateSlowCalculation("ADD", a, b);
        return a + b;
    }

    @Cacheable("subtractions")
    public double subtract(double a, double b) {
        simulateSlowCalculation("SUBTRACT", a, b);
        return a - b;
    }

    @Cacheable("multiplications")
    public double multiply(double a, double b) {
        simulateSlowCalculation("MULTIPLY", a, b);
        return a * b;
    }

    @Cacheable("divisions")
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division durch Null!");
        }
        simulateSlowCalculation("DIVIDE", a, b);
        return a / b;
    }

    @Cacheable("powers")
    public double power(double base, double exponent) {
        simulateSlowCalculation("POWER", base, exponent);
        return Math.pow(base, exponent);
    }

    @CacheEvict(value = {"additions", "subtractions", "multiplications", "divisions", "powers"}, allEntries = true)
    public void clearAllCaches() {
        System.out.println("Cache wurde geleert!");
    }

    private void simulateSlowCalculation(String operation, double a, double b) {
        System.out.println("BERECHNUNG LAEUFT: " + operation + "(" + a + ", " + b + ")");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
