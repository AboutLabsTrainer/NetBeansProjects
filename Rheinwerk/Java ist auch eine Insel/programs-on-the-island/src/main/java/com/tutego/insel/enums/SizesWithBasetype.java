package com.tutego.insel.enums;

public class SizesWithBasetype {

  sealed interface DrinkSize permits CoffeeSize, TeeSize {}
  enum CoffeeSize implements DrinkSize {SMALL, LARGE}
  enum TeeSize implements DrinkSize {SMALL, LARGE}

  static void orderSize( DrinkSize size ) {
    switch ( size ) {
      case CoffeeSize.SMALL -> System.out.println( "Small" );
      case TeeSize.SMALL -> System.out.println( "Small" );
      case CoffeeSize.LARGE -> System.out.println( "Large" );
      case TeeSize.LARGE -> System.out.println( "Large" );
    }
  }

  public static void main( String[] args ) {
    DrinkSize size = CoffeeSize.SMALL;
    orderSize( size );
  }
}
