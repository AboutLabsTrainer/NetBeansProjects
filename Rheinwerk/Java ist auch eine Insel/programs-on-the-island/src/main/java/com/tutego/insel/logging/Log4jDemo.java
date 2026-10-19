package com.tutego.insel.logging;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.MILLIS;
import java.time.Instant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jDemo {

  private final static Logger log = LogManager.getLogger( Log4jDemo.class );

  public static void main( String[] args ) {
    Instant start = now();
    log.info( "Wir starten mit Log4j" );

    try {
      log.info( "In {} Sekunden geht es los", 0 );
      throw null;
    }
    catch ( Exception e ) {
      log.error( "Oh Oh", e );
    }

    log.info( "Laufzeit {} ms", () -> start.until( now(), MILLIS ) );
  }
}