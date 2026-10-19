package com.tutego.insel.lang;

import java.lang.StackWalker.StackFrame;
import java.util.List;
import java.util.stream.Collectors;

public class GetStackFrames {

  public static void showTrace() {
    List<StackFrame> frames = StackWalker.getInstance().walk( stream  -> stream.collect( Collectors.toList() ) );
    for ( StackFrame stackFrame : frames )
      System.out.println( stackFrame );
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