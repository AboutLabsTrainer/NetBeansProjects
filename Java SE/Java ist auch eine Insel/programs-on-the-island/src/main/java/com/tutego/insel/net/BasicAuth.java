package com.tutego.insel.net;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class BasicAuth {
  public static void main( String[] args ) throws IOException {
    Authenticator.setDefault( new Authenticator() {
      @Override protected PasswordAuthentication getPasswordAuthentication() {
        System.out.printf( "url=%s, host=%s, ip=%s, port=%s%n",
                           getRequestingURL(), getRequestingHost(),
                           getRequestingSite(), getRequestingPort() );

        return new PasswordAuthentication( "httpwatch", "abc".toCharArray() );
      }
    } );

    String url = "https://www.httpwatch.com/httpgallery/authentication/authenticatedimage/default.aspx";
    try ( Scanner scanner = new Scanner( URI.create( url ).toURL().openStream() ) ) {
      System.out.println( scanner.useDelimiter( "\\Z" ).next() );
    }
  }
}