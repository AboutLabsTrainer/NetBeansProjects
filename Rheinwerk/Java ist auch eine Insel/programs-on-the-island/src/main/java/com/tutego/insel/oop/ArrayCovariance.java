package com.tutego.insel.oop;

public class ArrayCovariance {

  public static void setFirst( Object[] array, Object element ) {
    array[ 0 ] = element;
  }

  public static void main( String[] args ) {
    Object[] objectArray = new Object[ 1 ];
    String[] stringArray = new String[ 1 ];
    System.out.println( stringArray instanceof String[] );  // true
    System.out.println( stringArray instanceof Object[] );  // true

    setFirst( stringArray, "Life won’t wait" );
    setFirst( objectArray, "Yes you can" );

    // setFirst( stringArray, new Object() );
    // Exception in thread "main" java.lang.ArrayStoreException: java.lang.Object
    // setFirst( stringArray, new StringBuilder( "Set clear targets" ) );
    // Exception in thread "main" java.lang.ArrayStoreException: java.lang.StringBuilder
  }
}