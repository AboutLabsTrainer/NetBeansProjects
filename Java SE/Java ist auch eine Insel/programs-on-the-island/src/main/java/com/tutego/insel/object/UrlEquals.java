package com.tutego.insel.object;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class UrlEquals {
  public static void main( String[] args ) throws MalformedURLException {
    URL url1 = URI.create( "http://tutego.com/" ).toURL();
    URL url2 = URI.create( "http://www.tutego.com/" ).toURL();
    System.out.println( url1.equals( url2 ) ); // true
  }
}
