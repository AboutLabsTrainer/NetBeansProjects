package com.tutego.insel.exception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class TimestampWriter2 {
  public static void writeTimestamp() throws IOException {
    String content = LocalDateTime.now() + "\n";
    Files.writeString( Path.of( "timestamps.txt" ),
                       content, StandardOpenOption.APPEND );
  }

  public static void main( String[] args ) {
    try {
      writeTimestamp();
    }
    catch ( IOException e ) { e.printStackTrace(); }
  }
}
