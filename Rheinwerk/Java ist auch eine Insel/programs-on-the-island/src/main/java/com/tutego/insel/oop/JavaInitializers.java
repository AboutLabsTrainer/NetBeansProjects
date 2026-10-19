package com.tutego.insel.oop;

public class JavaInitializers {

  static {
    System.out.println( "static initializer" );
  }

  {
    System.out.println( "instance initializer" );
  }

  JavaInitializers() {
    System.out.println( "constructor" );
  }

  public static void main( String[] args ) {
    new JavaInitializers();
    new JavaInitializers();
  }
}