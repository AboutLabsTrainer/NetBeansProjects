package com.tutego.insel.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatPattern {
  public static void main( String[] args ) {
    DateFormat dfmt = new SimpleDateFormat( "E', den' dd.MM.yy 'um' hh:mm:ss" );
    System.out.println( dfmt.format( new Date() ) );   // Sa.., den 22.01.22 um 10:53:54

    SimpleDateFormat sdfmt = new SimpleDateFormat();
    sdfmt.applyPattern( "EEEE', 'dd. MMMM yyyy hh:mm" );
    System.out.println( sdfmt.format( new Date() ) ); // Samstag, 22. Januar 2022 10:53
  }
}
