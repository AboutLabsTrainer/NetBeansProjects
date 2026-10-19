package com.tutego.insel.nio2;

import java.io.IOException;
import java.nio.file.*;

public class StandardOpenOptionCreateNewDemo {
  public static void main( String[] args ) throws IOException {
    Files.deleteIfExists( Paths.get( "opi.herbert.tmp" ) );
    Files.newOutputStream( Paths.get( "opi.herbert.tmp" ) ).close();
    Files.newOutputStream( Paths.get( "opi.herbert.tmp" ) ).close();
    Files.newOutputStream( Paths.get( "opi.herbert.tmp" ), StandardOpenOption.CREATE_NEW ).close();
  }
}