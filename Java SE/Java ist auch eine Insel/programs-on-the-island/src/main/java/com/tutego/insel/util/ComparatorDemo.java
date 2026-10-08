package com.tutego.insel.util;

import java.util.*;

public class ComparatorDemo {
  public static void main( String[] args ) {
    List<Candy> list = Arrays.asList( new Candy( 100 ), new Candy( 1123 ), new Candy( 123 ) );

    Comparator<Candy> comp = Comparator.comparingInt( candy -> candy.calories() );
    // Comparator<Candy> comp = Comparator.comparingInt( Candy::calories );
    list.sort( comp );
    System.out.println( list.get( 0 ).calories() ); // 100
  }
}
