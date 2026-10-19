package com.tutego.insel.logging;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.MILLIS;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JDemo {

  private final static Logger log = LoggerFactory.getLogger( SLF4JDemo.class );

  public static void main( String[] args ) {
    Instant start = now();
    log.info( "Wir starten mit SLJF4" );

    try {
      log.info( "In {} Sekunden geht es los", 0 );
      throw null;
    }
    catch ( Exception e ) {
      log.error( "Oh Oh", e );
    }

    log.info( "Laufzeit {} ms", start.until( now(), MILLIS ) );
  }
}