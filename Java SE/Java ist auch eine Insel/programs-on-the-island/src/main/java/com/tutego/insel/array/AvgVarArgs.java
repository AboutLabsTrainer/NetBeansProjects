package com.tutego.insel.array;

public class AvgVarArgs {

  static double avg( double... numbers ) {
    if ( numbers == null || numbers.length == 0 )
      throw new IllegalArgumentException( "Array null or empty" );

    double sum = 0;

    for ( double n : numbers )
      sum += n;

    return sum / numbers.length;
  }

  public static void main( String[] args ) {
    System.out.println( avg( 1, 2, 9, 3 ) ); // 3.75
  }
}