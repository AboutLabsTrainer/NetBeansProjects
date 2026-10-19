package com.tutego.insel.exception;

import java.io.IOException;
import java.net.*;

public class NestedException {

  public static boolean isAvailable( String url ) {
    try {
      HttpURLConnection.setFollowRedirects( false );
      HttpURLConnection con = (HttpURLConnection) (URI.create(url).toURL().openConnection());
      return con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
    catch ( IOException e ) {
      throw new RuntimeException( e );
      //      RuntimeException e2 = new RuntimeException();
      //      e2.initCause( e );
      //      throw e2;
    }
  }

  public static void main( String[] args ) {
    System.out.println( isAvailable( "http://caliginous.junk/" ) ); // false
    System.out.println( isAvailable( "http://www.tutego.com/" ) ); // true
    System.out.println( isAvailable( "pigeon://germany/dortmund/huelshof/28/" ) ); //
  }
}
