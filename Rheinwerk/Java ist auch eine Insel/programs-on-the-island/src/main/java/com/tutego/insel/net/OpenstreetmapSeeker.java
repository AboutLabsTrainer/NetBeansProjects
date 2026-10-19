package com.tutego.insel.net;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.*;

public class OpenstreetmapSeeker {

  public static void main( String[] args ) throws Exception {

    String search = args.length > 0 ? String.join( "_", args ) : "sonsbeck";
    search = URLEncoder.encode( search, StandardCharsets.UTF_8.name() );
    String baseUrl = "https://nominatim.openstreetmap.org/search.php?q=";
    URL url = URI.create( baseUrl + search ).toURL();
    Pattern pattern = Pattern.compile( "<span class=\"name\">(.+?)</span>" );
    try ( Scanner scanner = new Scanner( url.openStream(),
                                         StandardCharsets.UTF_8.name() ) ) {
      scanner.findAll( pattern )
             .map( matchresult -> matchresult.group( 1 ) )
             .forEach( System.out::println );
    }
  }
}