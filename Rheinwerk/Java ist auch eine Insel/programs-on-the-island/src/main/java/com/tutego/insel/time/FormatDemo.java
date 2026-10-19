package com.tutego.insel.time;

import static java.lang.System.out;
import static java.time.format.DateTimeFormatter.*;
import static java.time.format.FormatStyle.*;
import static java.util.Locale.FRANCE;
import java.time.*;

public class FormatDemo {

  public static void main( String[] args ) {

    LocalDateTime now = LocalDateTime.now();
    out.println( now.format( BASIC_ISO_DATE ) );
    out.println( now.format( ISO_LOCAL_DATE_TIME ) );
    out.println( now.format( ofLocalizedDate( SHORT ) ) );
    out.println( now.format( ofLocalizedDateTime( MEDIUM ) ) );
    ZoneId id = ZoneId.of( "America/Cayenne" );
    out.println( now.format( ofLocalizedDateTime( FULL ).withLocale( FRANCE )
                                                        .withZone( id ) ) );

  }

}
