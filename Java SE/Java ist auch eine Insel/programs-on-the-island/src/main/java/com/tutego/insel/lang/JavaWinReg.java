package com.tutego.insel.lang;

import java.io.IOException;
import java.util.Scanner;

public class JavaWinReg {

  public static void main( String[] args ) throws IOException {
    ProcessBuilder builder = new ProcessBuilder( "reg", "query",
                                                 "\"HKEY_CURRENT_USER\\Control Panel\\Desktop\"",
                                                 "/v", "Wallpaper" );
    Process p = builder.start();
    try ( Scanner scanner = new Scanner( p.getInputStream() ) ) {
      scanner.findWithinHorizon( "Wallpaper\\s+REG_SZ\\s+", 0 ); 
      System.out.println( scanner.next() );
    }
  }
}
