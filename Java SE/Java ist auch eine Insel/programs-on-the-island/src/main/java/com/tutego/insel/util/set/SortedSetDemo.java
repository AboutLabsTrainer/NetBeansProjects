package com.tutego.insel.util.set;

import java.time.LocalDate;
import java.time.Month;
import java.util.NavigableSet;
import java.util.TreeSet;

public class SortedSetDemo {
  public static void main( String[] args ) {
    NavigableSet<LocalDate> set = new TreeSet<>();
    set.add( LocalDate.of( 2018, Month.MARCH, 10 ) );
    set.add( LocalDate.of( 2018, Month.MARCH, 12 ) );
    set.add( LocalDate.of( 2018, Month.MARCH, 14 ) );
    
    LocalDate cal1 = set.lower( LocalDate.of( 2018, Month.MARCH, 12 ) );
    System.out.printf( "%tF%n", cal1 );   // 2018-03-10
    
    LocalDate cal2 = set.ceiling( LocalDate.of( 2018, Month.MARCH, 12 ) );
    System.out.printf( "%tF%n", cal2 );   // 2018-03-12
    
    LocalDate cal3 = set.floor( LocalDate.of( 2018, Month.MARCH, 12 ) );
    System.out.printf( "%tF%n", cal3 );   // 2018-03-12
    
    LocalDate cal4 = set.higher( LocalDate.of( 2018, Month.MARCH, 12 ) );
    System.out.printf( "%tF%n", cal4 );   // 2018-03-14
  }
}
