package de.ibb.tag08;

import de.ibb.tag08.level1.Level1Demo;
import de.ibb.tag08.level2.Level2Demo;
import de.ibb.tag08.level3.Level3Demo;

/**
 * Einstiegspunkt für alle drei Level der Mini-Challenge.
 */
public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Tag 8: Annotations & Multithreading ===");

        System.out.println("\n--- Level 1: @Author & Threads ---");
        Level1Demo.run();

        System.out.println("\n--- Level 2: @Validate & Thread-Pool ---");
        Level2Demo.run();

        System.out.println("\n--- Level 3: Logging, Producer-Consumer, Performance ---");
        Level3Demo.run();

        System.out.println("\n=== Ende Tag 8 Demo ===");
    }
}

