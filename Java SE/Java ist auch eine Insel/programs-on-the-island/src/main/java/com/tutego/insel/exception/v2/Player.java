package com.tutego.insel.exception.v2;

class Player {

  int age;

  Player( int age ) {
    if ( age <= 0 )
      throw new PlayerException( "No age <= 0 allowed, age must be positive or 0" );
  
    this.age = age;
  }

  public static void main( String[] args ) {
    try {
      Player d = new Player( -100 );
      System.out.println( d );
    }
    catch ( IllegalArgumentException e ) {
      e.printStackTrace();
    }
  }
}