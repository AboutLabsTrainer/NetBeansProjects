package com.tutego.insel.date;

import static java.lang.System.out;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeInstance {

  public static void main( String[] args ) {
    Date d = new Date();

    DateFormat df;
    df = DateFormat.getDateTimeInstance( /* dateStyle */ DateFormat.FULL,
                                         /* timeStyle */ DateFormat.MEDIUM );
    out.println( df.format(d) ); // Donnerstag, 6. September 2018, 15:38:25
    
    df = DateFormat.getDateTimeInstance( DateFormat.FULL, DateFormat.MEDIUM, Locale.ITALY );
    out.println( df.format(d) ); // giovedì 6 settembre 2018, 15:38:25

    
    df = DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.FULL, Locale.CANADA_FRENCH );
    out.println( df.format(d) ); // 18-09-06 15 h 38 min 25 s heure avancée d’Europe centrale
  }
}
