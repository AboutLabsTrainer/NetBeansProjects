package com.tutego.insel.enums;

public class Sizes {

  enum CoffeeSize {SMALL, LARGE}

  enum TeeSize {SMALL, LARGE}

  static void orderSize( Enum size ) {
    switch ( size ) {
      case CoffeeSize.SMALL -> System.out.println( "Small" );
      case TeeSize.SMALL -> System.out.println( "Small" );
      case CoffeeSize.LARGE -> System.out.println( "Large" );
      case TeeSize.LARGE -> System.out.println( "Large" );
      default -> { }
    }
  }

  public static void main( String[] args ) {
    Enum size = CoffeeSize.SMALL;
    orderSize( size );
  }
}
