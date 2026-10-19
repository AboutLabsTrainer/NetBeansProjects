package com.tutego.insel.oop;

class StaticBlock {

  static {
    System.out.println( "Two fish are in a tank." );
  }

  public static void main( String[] args ) {
    System.out.println( "‘How do you drive this thing?’" );
  }

  static {
    System.out.print( "One says, " );
  }
}
