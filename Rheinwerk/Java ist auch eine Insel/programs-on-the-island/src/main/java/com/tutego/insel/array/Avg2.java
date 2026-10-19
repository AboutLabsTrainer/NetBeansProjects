package com.tutego.insel.array;

public class Avg2 {

  static double avg( double[] numbers ) {
    if ( numbers == null || numbers.length == 0 )
      throw new IllegalArgumentException( "Array null or empty" );

    double sum = 0;

    for ( double n : numbers )
      sum += n;

    return sum / numbers.length;
  }

  public static void main( String[] args ) {
    double avg = avg( new double[]{ 2, 3, 4} );
    System.out.println( avg );
  }
}