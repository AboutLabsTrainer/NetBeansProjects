package com.tutego.insel.exception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class TimestampWriter {
  public static void writeTimestamp() {
    String content = LocalDateTime.now() + "\n";
    try {
      Files.writeString( Path.of( "timestamps.txt" ),
                         content, StandardOpenOption.APPEND );
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
  }
  
  public static void main( String[] args ) {
    writeTimestamp();
  }
}
