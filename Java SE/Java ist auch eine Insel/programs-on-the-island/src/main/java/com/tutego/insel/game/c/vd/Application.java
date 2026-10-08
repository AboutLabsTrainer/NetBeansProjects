package com.tutego.insel.game.c.vd;

public class Application {
  public static void main( String[] args ) {
    Candy sugarDaddy = new Candy();
    sugarDaddy.name = "Sugar Daddy Caramel Pops";
    sugarDaddy.price = 20;

    Candy caramelPops = new Candy( sugarDaddy );
    System.out.printf( "%s %d%n",      // Sugar Daddy Caramel Pops 20
                       caramelPops.name, caramelPops.price );
  }
}
