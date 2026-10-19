package com.tutego.insel.exception;
import java.net.*;

public class RethrowFillInStackTrace {

  public static URI createUriFromHost( String host ) throws URISyntaxException {
    try {
      return new URI( "http://" + host );
    }
    catch ( URISyntaxException e ) {
      System.err.println( "Help! " + e.getMessage() );
      e.fillInStackTrace();
      throw e;
    }
  }

  public static void main( String[] args ) {
    try {
      createUriFromHost( "tutego.de" );
      createUriFromHost( "%" );
    }
    catch ( URISyntaxException e ) {
      e.printStackTrace();
    }
  }
}