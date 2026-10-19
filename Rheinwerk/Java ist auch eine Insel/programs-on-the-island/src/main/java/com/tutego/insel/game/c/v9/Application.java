package com.tutego.insel.game.c.v9;

import java.awt.*;

public class Application {
  public static void main( String[] args ) {
    Candy candy = new Candy();
    candy.candyType = CandyType.CARAMELS;                 // :)
    candy.candyType = 1;                                  // :-|
    candy.candyType = Cursor.DEFAULT_CURSOR               // :-@
                      + GridBagConstraints.PAGE_END / Character.LETTER_NUMBER;
    candy.price = CandyType.CARAMELS ^ CandyType.GUMMIES; // :-O
    System.out.println( candy.candyType );
  }
}
