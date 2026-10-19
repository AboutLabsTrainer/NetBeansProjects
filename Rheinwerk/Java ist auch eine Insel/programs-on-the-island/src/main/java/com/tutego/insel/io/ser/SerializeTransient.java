package com.tutego.insel.io.ser;

import java.io.*;
import java.util.Arrays;

class NotTransientNotSerializable implements Serializable {

  Thread t = new Thread();
  // transient Thread t = new Thread();
  String s = "Fremde sind Freunde, die man nur noch nicht kennengelernt hat.";
}

public class SerializeTransient {
  public static void main( String[] args ) {
    ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
    try ( ObjectOutputStream oos = new ObjectOutputStream( bytearray ) ) {
      oos.writeObject( new NotTransientNotSerializable() );
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
    System.out.println( Arrays.toString( bytearray.toByteArray() ) );
  }
}