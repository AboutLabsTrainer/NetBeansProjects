package com.tutego.insel.array;

public class Avg1 {

  static double avg( double[] numbers ) {
    double sum = 0;

    for ( int i = 0; i < numbers.length; i++ )
      sum += numbers[ i ];

    return sum / numbers.length;
  }

  public static void main( String[] args ) {
    double[] prices = { 2, 3, 4 };
    System.out.println( avg( prices ) );            // 3.0
  }
}
