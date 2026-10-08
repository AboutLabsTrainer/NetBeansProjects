/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.island.ready;

/**
 *
 * @author Trainer
 */
public class SmallConversation {

    public static void main(String[] args) {
        System.out.println("Hey! What's your favorite candy?");
        String name = new java.util.Scanner(System.in).nextLine();
        System.out.printf("How much %s candy can you eat?%n", name);
        int quantity = new java.util.Scanner(System.in).nextInt();
        System.out.printf("I see, %s. And how much did that cost?%n", quantity);
        double value = new java.util.Scanner(System.in).nextDouble();
        System.out.printf("%s? I see, I was guessing %s.%n",
                value, value * 2 / 3);
    }
}
