package com.tutego.insel.net;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketTest {

  public static void main( String[] args ) throws IOException {

    final String ATOMIC_TIME_SERVER = "time-c.nist.gov";
    final int ATOMIC_TIME_SERVER_PORT = 13;

    try ( Socket socket = new Socket( ATOMIC_TIME_SERVER, ATOMIC_TIME_SERVER_PORT );
          InputStream is = socket.getInputStream();
          Scanner input = new Scanner( is, StandardCharsets.US_ASCII ) ) {

      input.nextLine(); // Skip first empty line

      String line = input.nextLine();
      System.out.println( line );
    }
  }
}
