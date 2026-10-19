package com.tutego.insel.enums;

import static com.tutego.insel.enums.GameCurrency.*;

public class GameCurrencyDemo {
  
  public static void main( String[] args ) {
    System.out.println( EURO.convertTo( EURO, 12 ) );               // 12.0
    System.out.println( EURO.convertTo( PONRODOLLAR, 12 ) );        // 6.0
    System.out.println( PONRODOLLAR.convertTo( EURO, 12 ) );        // 24.0
    System.out.println( PONRODOLLAR.convertTo( PONRODOLLAR, 12 ) ); // 12.0
  }
}
