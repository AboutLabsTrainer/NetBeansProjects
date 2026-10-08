/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.island.ready;

/**
 *
 * @author Trainer
 */
public class PrintTheAverage {

    public static void main(String[] args) {
        double[] numbers = {1.9, 7.8, 2.4, 9.3};
        double sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        double avg = sum / numbers.length;
        System.out.println(avg); // 5.35
    }
}
