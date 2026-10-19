package com.tutego.insel.game.c.v7;

public class Application {
  public static void main( String[] args ) {
    Candy lollipop = new Candy();
    lollipop.setPrice( 12 );
    System.out.println( lollipop.getPrice() * lollipop.getQuantity() ); // 12
  }
}
