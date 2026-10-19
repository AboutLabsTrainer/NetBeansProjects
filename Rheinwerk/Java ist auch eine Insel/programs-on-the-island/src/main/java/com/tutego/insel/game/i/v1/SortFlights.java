package com.tutego.insel.game.i.v1;

import java.util.Arrays;

public class SortFlights {
  public static void main( String[] args ) {
    Flight londonToDurban = new Flight( 1200 );
    Flight dortmundToBrussels = new Flight( 200 );
    Flight berlinToNairobi = new Flight( 1500 );
    Flight duesseldorfToWindhoek = new Flight( 1400 );
    Flight[] flights = {
        londonToDurban, dortmundToBrussels, berlinToNairobi, duesseldorfToWindhoek
    };
    Arrays.sort( flights );
    for ( Flight flight : flights )
      System.out.print( (int) flight.price() + " "); // 200 1200 1400 1500
  }
}
