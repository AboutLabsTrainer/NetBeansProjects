package com.tutego.insel.game.c.vb;

class Candy {
  Candy() {
    System.out.println( "2. inside the constructor" );
  }

  void eat() {
    System.out.println( "4. eating" );
  }

  public static void main( String[] args ) {
    System.out.println( "1. before the constructor call" );
    Candy d = new Candy ();
    System.out.println( "3. after the constructor call" );
    d.eat();
    System.out.println( "5. tummy ache" );
  }
}
