package com.tutego.insel.util;

import java.util.*;

public class Primtest {
  public static void main( String[] args ) {
    final int    MAXPRIM  = 1000;
    final int    ROOT     = (int) Math.sqrt( MAXPRIM );
    final BitSet nonPrimes = new BitSet();               // Nicht-Primzahlen
    
    for ( int i = 2; i <= ROOT; i = nonPrimes.nextClearBit( i + 1 ) )
      for ( int j = 2 * i; j <= MAXPRIM; j += i ) // Alle Vielfachen sind keine Primzahlen
        nonPrimes.set( j );
    
    for ( int i = 2; i <= MAXPRIM; i = nonPrimes.nextClearBit( i + 1 ) )
      System.out.printf( "%d ", i );
  }
}
