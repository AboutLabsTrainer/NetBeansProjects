package com.tutego.insel.game.c.vq;

public class Playground {
  public static void main( String[] args ) {
    Candy liquorice = new Candy();
    liquorice.quantity = 10;
    liquorice.name = "Salzige Leckmuschel";
    Player peter = new Player();
    peter.candy = liquorice;

    Event mugging = new Mugging();
    Event gift    = new Gift();

    System.out.println( peter.candy.quantity ); // 10
    mugging.process( peter );
    System.out.println( peter.candy.quantity ); // 0
    gift.process( peter );
    System.out.println( peter.candy.quantity ); // 7
  }
}
