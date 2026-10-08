package com.tutego.insel.json;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

public class JsonGeneratorDemo {

  public static void main( String[] args ) {

    JsonGenerator gen = Json.createGenerator( System.out );
    gen.writeStartArray();
     gen.writeStartObject();
      gen.write( "x", "12" );
      gen.write( "y", "2" );
     gen.writeEnd();
     gen.writeStartObject()
         .write( "x", "99" )
         .write( "x", "123" )
        .writeEnd();
    gen.writeEnd().close();
    // [{"x":"12","y":"2"},{"x":"99","x":"123"}]
  }
}
