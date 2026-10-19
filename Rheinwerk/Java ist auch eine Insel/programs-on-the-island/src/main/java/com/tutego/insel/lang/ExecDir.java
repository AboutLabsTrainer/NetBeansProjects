package com.tutego.insel.lang;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ExecDir {

  public static void main( String[] args ) {
    ProcessBuilder builder = new ProcessBuilder( "cmd", "/c", "dir" );
    builder.directory( new File( "c:/" ) );
    try {
      Process process = builder.start();
      try ( InputStream in = process.getInputStream() ) {
        in.transferTo( System.out );
      }
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
  }
}