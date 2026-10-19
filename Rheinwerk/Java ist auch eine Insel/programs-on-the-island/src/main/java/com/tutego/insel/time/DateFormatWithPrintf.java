package com.tutego.insel.time;

import java.time.LocalDateTime;
import java.util.Locale;

public class DateFormatWithPrintf {

  public static void main( String[] args ) {
    LocalDateTime now = LocalDateTime.now();
    System.out.printf( "%tR%n", now );                 // 14:59
    System.out.printf( "%tT%n", now );                 // 14:59:19
    System.out.printf( "%tD%n", now );                 // 09/06/18
    System.out.printf( "%tF%n", now );                 // 2018-09-06
    System.out.printf( Locale.CHINA, "%tD%n", now );   // 09/06/18
    System.out.printf( Locale.GERMANY, "%tD%n", now ); // 09/06/18
  }
}