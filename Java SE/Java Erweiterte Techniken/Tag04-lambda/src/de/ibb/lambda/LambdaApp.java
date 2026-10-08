package de.ibb.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Musterlösung zur Übung "Funktionale Schnittstellen und Lambdas".
 */
public class LambdaApp {

    public static void main(String[] args) {

        // -----------------------------
        // Produktliste
        // -----------------------------
        List<Product> products = new ArrayList<>();
        products.add(new Product("Java-Buch", 29.90, "Buch"));
        products.add(new Product("Monitor 27\"", 199.00, "Elektronik"));
        products.add(new Product("Kaffeemaschine", 79.90, "Haushalt"));
        products.add(new Product("Maus", 19.99, "Elektronik"));
        products.add(new Product("Roman", 12.50, "Buch"));

        // -----------------------------
        // Eigene funktionale Schnittstelle PriceOperation
        // -----------------------------
        PriceOperation mwst19 = p -> p * 1.19;
        PriceOperation rabatt10 = p -> p * 0.90;
        PriceOperation versandPauschal = p -> p + 4.95;

        Product beispiel = products.get(0);

        double preisMitMwst = berechnePreis(beispiel, mwst19);
        double preisMitRabatt = berechnePreis(beispiel, rabatt10);
        double preisRabattUndMwst = mwst19.apply(preisMitRabatt);

        System.out.println("=== PriceOperation-Beispiele für: " + beispiel.getName() + " ===");
        System.out.printf("Originalpreis:        %.2f €%n", beispiel.getPrice());
        System.out.printf("mit 19%% MwSt:        %.2f €%n", preisMitMwst);
        System.out.printf("mit 10%% Rabatt:      %.2f €%n", preisMitRabatt);
        System.out.printf("Rabatt + dann MwSt:  %.2f €%n", preisRabattUndMwst);
        System.out.printf("Rabatt + MwSt + Versand: %.2f €%n",
                versandPauschal.apply(preisRabattUndMwst));

        // -----------------------------
        // Consumer<Product> – schöne Ausgabe
        // -----------------------------
        Consumer<Product> prettyPrint = p ->
                System.out.printf("%-15s (%-10s): %.2f €%n",
                        p.getName(), p.getCategory(), p.getPrice());

        System.out.println("\n=== Alle Produkte (pretty print) ===");
        products.forEach(prettyPrint);

        // -----------------------------
        // Predicate<Product> – Filtern
        // -----------------------------
        Predicate<Product> teuer = p -> p.getPrice() > 50.0;
        Predicate<Product> istBuch = p -> "Buch".equalsIgnoreCase(p.getCategory());

        List<Product> teureProdukte = filterProducts(products, teuer);
        List<Product> teureBuecher = filterProducts(products, teuer.and(istBuch));

        System.out.println("\n=== Teure Produkte (> 50 €) ===");
        teureProdukte.forEach(prettyPrint);

        System.out.println("\n=== Teure Bücher (> 50 €) ===");
        teureBuecher.forEach(prettyPrint);

        // -----------------------------
        // Function<Product,String> – Mapping
        // -----------------------------
        Function<Product, String> productToLine =
                p -> String.format("[%s] %s – %.2f €",
                        p.getCategory(), p.getName(), p.getPrice());

        List<String> produktZeilen = mapProducts(products, productToLine);

        System.out.println("\n=== Produktzeilen (Function) ===");
        produktZeilen.forEach(System.out::println);

        // -----------------------------
        // Supplier<Integer> – Bestellnummern
        // -----------------------------
        final int[] currentOrder = {1000};
        Supplier<Integer> orderNumberSupplier = () -> currentOrder[0]++;

        System.out.println("\n=== Bestellnummern (Supplier) ===");
        for (int i = 0; i < 5; i++) {
            System.out.println("Neue Bestellnummer: " + orderNumberSupplier.get());
        }
    }

    /**
     * Nimmt den Basispreis des Produkts und wendet die PriceOperation an.
     */
    static double berechnePreis(Product product, PriceOperation op) {
        return op.apply(product.getPrice());
    }

    /**
     * Filtert Produkte mit einem Predicate.
     */
    static List<Product> filterProducts(List<Product> products,
                                        Predicate<Product> predicate) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (predicate.test(p)) {
                result.add(p);
            }
        }
        return result;
    }

    /**
     * Wendet ein Mapping auf alle Produkte an.
     */
    static <R> List<R> mapProducts(List<Product> products,
                                   Function<Product, R> mapper) {
        List<R> result = new ArrayList<>();
        for (Product p : products) {
            result.add(mapper.apply(p));
        }
        return result;
    }
}