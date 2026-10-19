package com.tutego.insel.json;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import javax.json.Json;
import javax.json.stream.JsonParser;

public class JsonParserDemo {

  public static void main( String[] args ) throws IOException {

    URL url = URI.create(
        "https://data.cityofnewyork.us/api/views/25th-nujf/rows.json?accessType=DOWNLOAD"
    ).toURL();
    
    try ( JsonParser parser = Json.createParser( url.openStream() ) ) {
      while ( parser.hasNext() ) {
        switch ( parser.next() ) {
          case KEY_NAME:
          case VALUE_STRING:
            System.out.println( parser.getString() );
            break;
          case VALUE_NUMBER:
            System.out.println( parser.getBigDecimal() );
            break;
          case VALUE_TRUE:
            System.out.println( true );
            break;
          case VALUE_FALSE:
            System.out.println( false );
            break;
          case VALUE_NULL:
          case START_ARRAY:
          case END_ARRAY:
          case START_OBJECT:
          case END_OBJECT:
            // Ignore
        }
      }
    }
  }
}
