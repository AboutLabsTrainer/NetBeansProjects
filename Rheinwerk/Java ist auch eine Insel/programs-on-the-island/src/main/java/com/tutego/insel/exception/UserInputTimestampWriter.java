package com.tutego.insel.exception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputTimestampWriter {
  public static void writeTimestamp() {
    Path path = Path.of( "timestamps.txt" );
    try {
      int count = new Scanner( System.in ).nextInt();
      for ( int i = 0; i < count; i++ ) {
        Files.writeString( path, LocalDateTime.now() + "\n",
                           StandardOpenOption.APPEND );
      }
    }
    catch ( InputMismatchException e ) {
      System.err.println( "Input was not an integer" );
    }
    catch ( IOException e ) {
      System.err.println( "Error when writing to the file" );
    }
  }

  public static void main( String[] args ) {
    writeTimestamp();
  }
}
