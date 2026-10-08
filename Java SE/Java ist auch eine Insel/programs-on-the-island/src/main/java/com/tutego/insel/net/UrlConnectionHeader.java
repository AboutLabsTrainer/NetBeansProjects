package com.tutego.insel.net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;

public class UrlConnectionHeader {
  public static void main( String[] args ) throws IOException {
    URL url = URI.create( "http://www.tutego.com/index.html" ).toURL();
    URLConnection con = url.openConnection();
    
    PrintStream out = System.out;
    out.println( con );
    out.println( "Date            : " + Instant.ofEpochMilli( con.getDate() ) );
    out.println( "Last Modified   : " + Instant.ofEpochMilli( con.getLastModified() ) );
    out.println( "Content encoding: " + con.getContentEncoding() );
    out.println( "Content type    : " + con.getContentType() );
    out.println( "Content length  : " + con.getContentLength() );
  }
}
