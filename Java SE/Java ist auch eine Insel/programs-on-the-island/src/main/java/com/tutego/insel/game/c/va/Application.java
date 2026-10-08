package com.tutego.insel.game.c.va;

public class Application {
  public static void main( String[] args ) {
    CandyType amedei = CandyType.CHOCOLATE;

    if ( amedei == CandyType.CHOCOLATE )
      System.out.println( "Whew, that's expensive!" );

    CandyType werther = CandyType.CARAMELS;
    switch ( werther ) {
      case CARAMELS:
//      case CandyType.CARAMELS:
      case CHOCOLATE: System.out.println( "caramel/chocolate" );
    }
    switch ( werther ) {
      case CARAMELS, CHOCOLATE: System.out.println( "caramel/chocolate" );
    }
    switch ( werther ) {
      case CARAMELS, CHOCOLATE -> System.out.println( "caramel/chocolate" );
    }
  }

  public static boolean isUnknownCandyType( CandyType type ) {
    return type == CandyType.OTHER;
  }
}
