package com.tutego.insel.string;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

class StreamTokenizerDemo {
  public static void main( String[] args ) throws IOException {
    URL url = URI.create( "http://www.tutego.com/index.html" ).toURL();
    Reader reader = new InputStreamReader( url.openStream(), StandardCharsets.UTF_8 );
    StreamTokenizer st = new StreamTokenizer( reader );
    
    //     st.slashSlashComments( true ); */
    st.slashStarComments( true );
    st.ordinaryChar( '/' );
    st.parseNumbers();
    st.eolIsSignificant( true );
    
    for ( int ttype; (ttype = st.nextToken()) != StreamTokenizer.TT_EOF; ) {
      switch ( ttype ) {
        case StreamTokenizer.TT_NUMBER:
          System.out.println( "Nummer: " + st.nval );
          break;
        case StreamTokenizer.TT_WORD:
          System.out.println( "Wort: " + st.sval );
          break;
        case StreamTokenizer.TT_EOL:
          System.out.println( "Zeilenende" );
          break;
        default :
          System.out.println( "Zeichen: " + (char) st.ttype );
      }
    }
  }
}