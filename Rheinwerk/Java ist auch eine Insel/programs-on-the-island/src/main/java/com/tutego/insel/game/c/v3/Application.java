package com.tutego.insel.game.c.v3;

class Application {
  public static void main( String[] args ) {
    Candy lollipop = new Candy();
    lollipop.name = "Lollipop";
    lollipop.price = 12;
    lollipop.quantity = 2;
    System.out.println( lollipop.totalPrice() ); // 24
    lollipop.resetQuantity();
    System.out.println( lollipop.totalPrice() ); // 12
  }
}
