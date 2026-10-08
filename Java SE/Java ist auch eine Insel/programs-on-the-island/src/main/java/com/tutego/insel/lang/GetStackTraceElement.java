package com.tutego.insel.lang;

public class GetStackTraceElement {
  
  public static void showTrace() {
    for ( StackTraceElement trace : Thread.currentThread().getStackTrace() )
      System.out.println( trace );
  }

  public static void m( int n ) {
    if ( n == 0 )
      showTrace();
    else
      m( n - 1 );
  }

  public static void main( String[] args ) {
    m( 2 );
  }
}