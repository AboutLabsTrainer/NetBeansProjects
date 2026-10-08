/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.island.ready;

/**
 *
 * @author Trainer
 */
public class MatrixArray {

    public static void main(String[] args) {
        // Beispiel: 2x3 Matrix
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6, 7}
        };
        
// Durchlaufen der Matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
                if (i == 0 && j == 0) {
                    
                }
            }
            System.out.println(); // Neue Zeile
        }

    }
}
