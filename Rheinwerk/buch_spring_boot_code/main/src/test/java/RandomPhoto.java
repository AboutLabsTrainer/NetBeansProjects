import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

class RandomPhoto {

  private final Logger log = LoggerFactory.getLogger( getClass() );
  private int attemptCounter = 1;

  public String receive( String gender ) throws IOException {
    log.info( "Attempts: {}", attemptCounter++ );
    if ( attemptCounter <= 3 )
      throw new IOException( "Not yet ready to serve" );
    var url = "https://randomuser.me/api/?inc=picture&noinfo&gender=" + gender;
    try ( var inputStream = URI.create( url ).toURL().openStream() ) {
      return StreamUtils.copyToString( inputStream, StandardCharsets.UTF_8 );
    }
  }
}
