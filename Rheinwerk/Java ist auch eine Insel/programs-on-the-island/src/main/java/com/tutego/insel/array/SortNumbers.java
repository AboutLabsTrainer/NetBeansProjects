package com.tutego.insel.array;

import java.util.Arrays;

class SortNumbers {
  public static void main( String[] args ) {
    if ( args.length > 0 ) {
      double[] numbers = new double[ args.length ];
      for ( int i = 0; i < args.length; i++ )
        numbers[ i ] = Double.parseDouble( args[ i ] );
      Arrays.sort( numbers );
      for ( double number : numbers )
        System.out.println( number );
    }
  }
}
