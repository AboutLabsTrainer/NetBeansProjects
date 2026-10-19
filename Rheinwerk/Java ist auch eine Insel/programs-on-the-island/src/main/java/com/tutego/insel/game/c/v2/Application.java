package com.tutego.insel.game.c.v2;

class Application {
  public static void main( String[] args ) {
    Candy lollipop = new Candy();
    lollipop.name = "Lollipop";
    lollipop.price = 12;
    lollipop.quantity = 2;

    Candy licorice = new Candy();
    licorice.name = "Licorice";
    licorice.price = 22;

    System.out.printf( "%s, %d × %d = %d%n", // Lollipop, 2 × 12 = 24
                       lollipop.name, lollipop.quantity, lollipop.price,
                       lollipop.quantity * lollipop.price );
    System.out.printf( "%s, %d × %d = %d%n", // Licorice, 0 × 22 = 0
                       licorice.name, licorice.quantity, licorice.price,
                       licorice.quantity * licorice.price );
  }
}
