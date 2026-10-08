package com.tutego.insel.nested;

public class SquirrelNutCaramel {

  static String name = "Squirrel Nut Caramel";
  int invented = 1890;

  static class Peanut {
    void print() {
      System.out.println( name );
      // System.out.println( invented );
      // Non-static field 'invented' cannot be referenced from a static context
    }
  }

  public static void main( String[] args ) {
    Peanut peanut = new Peanut();
    SquirrelNutCaramel.Peanut peanut2 = new SquirrelNutCaramel.Peanut();
    peanut.print();
  }
}