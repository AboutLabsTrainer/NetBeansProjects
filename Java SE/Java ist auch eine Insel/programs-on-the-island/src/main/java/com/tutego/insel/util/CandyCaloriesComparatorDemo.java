package com.tutego.insel.util;

import java.util.*;

class CandyCaloriesComparator implements Comparator<Candy> {
  @Override public int compare( Candy candy1, Candy candy2 ) {
    return Integer.compare( candy1.calories(), candy2.calories() );
  }
}

public class CandyCaloriesComparatorDemo {

  public static void main( String[] args ) {
    Candy[] candies = { new Candy( 100 ), new Candy( 623 ), new Candy( 123 ) };
    Arrays.sort( candies, new CandyCaloriesComparator() );
    System.out.println( candies[ 0 ].calories() );      // 100
  }
}