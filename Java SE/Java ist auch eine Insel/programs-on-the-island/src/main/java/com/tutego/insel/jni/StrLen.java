package com.tutego.insel.jni;

public class StrLen {
  static {
    System.loadLibrary( "strlen" );
  }

  public static native int strlen( String s );
}