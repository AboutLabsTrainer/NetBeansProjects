/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.generics;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author Trainer
 */
public class ConsumerAndPredicate {
    
    public static void main(String[] args) {
        printCities();
    }
    
    // Consumer
    // https://medium.com/javarevisited/java-8s-consumer-predicate-supplier-and-function-bbc609a29ff9
    public static void printCities() {

        List<String> cities = new ArrayList<>();
        cities.add("Delhi");
        cities.add("Mumbai");
        cities.add("Goa");
        cities.add("Pune");

        Consumer<String> printConsumer= city -> System.out.println(city);    
        cities.forEach(printConsumer);
    }
    
}
