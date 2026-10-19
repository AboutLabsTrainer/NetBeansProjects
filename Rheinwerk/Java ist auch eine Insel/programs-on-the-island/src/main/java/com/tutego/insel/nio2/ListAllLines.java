package com.tutego.insel.nio2;

import java.io.IOException;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;

public class ListAllLines {
  public static void main( String[] args ) throws IOException, URISyntaxException {
    URI uri = ListAllLines.class.getResource( "/lyrics.txt" ).toURI();
    Path path = Paths.get( uri );
    System.out.printf( "File '%s' with size of %d byte(s) with lines:%n",
                       path.getFileName(), Files.size( path ) );
    int lineCnt = 1;
    for ( String line : Files.readAllLines( path, StandardCharsets.UTF_8 ) )
      System.out.println( lineCnt++ + ": " + line );
  }
}