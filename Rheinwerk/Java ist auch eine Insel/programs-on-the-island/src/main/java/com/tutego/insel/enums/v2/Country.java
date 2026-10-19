package com.tutego.insel.enums.v2;

public enum Country {

  GERMANY, UK, CHINA;

  {
    System.out.println( "Object initializer" );
  }

  static {
    System.out.println( "Class initializer" );
  }

  private Country() {
    System.out.println( "Constructor" );
  }

  public static void main( String[] args ) {
    System.out.println( GERMANY );
  }
}
