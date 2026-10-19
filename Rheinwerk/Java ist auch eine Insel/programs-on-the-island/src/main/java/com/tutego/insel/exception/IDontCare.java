package com.tutego.insel.exception;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

class IDontCare {

  public static void main( String[] args ) throws Exception {
    Files.writeString( Path.of( "timestamps.txt" ),
                       LocalDateTime.now() + "\n",
                       StandardOpenOption.APPEND );
  }
}
