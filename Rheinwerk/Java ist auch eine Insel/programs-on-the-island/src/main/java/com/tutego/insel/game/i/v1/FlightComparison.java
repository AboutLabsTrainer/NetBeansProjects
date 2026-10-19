package com.tutego.insel.game.i.v1;

public class FlightComparison {
  public static void main( String[] args ) {
    Flight londonToDurban = new Flight( 1200 );
    Flight dortmundToBrussels = new Flight( 200 );
    System.out.println( londonToDurban.compareTo( londonToDurban ) );        // 0
    System.out.println( londonToDurban.compareTo( dortmundToBrussels ) );    // 1
    System.out.println( dortmundToBrussels.compareTo( londonToDurban ) );    //-1
    System.out.println( dortmundToBrussels.compareTo( dortmundToBrussels ) );// 0
  }
}
