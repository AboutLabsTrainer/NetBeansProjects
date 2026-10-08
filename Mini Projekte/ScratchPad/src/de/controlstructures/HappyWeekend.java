/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.controlstructures;

/**
 *
 * @author Trainer
 */
public class HappyWeekend {
    public static void main(String[] args) {
        String[] tage = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        // Die Schleife liest automatisch jeden Tag nacheinander aus
        for (String tag : tage) {

            if (tag.equals("Friday")) {
                System.out.println("Schönes Wochenende!");
            }
        }
    }
}