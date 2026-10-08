package com.tutego.insel.nested;

public class Shoe {

  void output() {
    System.out.println( "Der Schuh des Manitu" );
  }
  
  class LeatherBoot extends Shoe {
    void what() {
      Shoe.this.output();
      ((Shoe)this).output();
    }

    @Override
    void output() {
      System.out.println( "Shoe.LeatherBoot." );
    }
  }

  public static void main( String[] args ) {
    new Shoe().new LeatherBoot().what();
  }
}
