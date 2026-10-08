package com.tutego.insel.meta;

class CheckClassType {
  public static void main( String[] args ) {
    checkClassType( CharSequence.class );
    checkClassType( String.class );
    checkClassType( int[][][].class );  // or (new int[2][3][4]).getClass()
    checkClassType( Integer.TYPE );
  }

  static void checkClassType( Class<?> c ) {
    if ( c.isArray() )
      System.out.println( c.getName() + " is an array" );
    else if ( c.isPrimitive() )
      System.out.println( c + " is a primitive type" );
    else if ( c.isInterface() )
      System.out.println( c.getName() + " is an interface" );
    else if ( c.isRecord() )
      System.out.println( c.getName() + " is a record" );
    else
      System.out.println( c.getName() + " is probably a class" );
  }
}