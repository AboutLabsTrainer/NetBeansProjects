package com.tutego.insel.lang;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class LoadClassMultipleTimes {

  public static Object newInstance( String path, String classname ) throws Exception {
    URL url = Paths.get( path ).toUri().toURL();
    try ( URLClassLoader cl = new URLClassLoader( new URL[]{ url } ) ) {
      Class<?> c = cl.loadClass( classname );
      return c.getConstructor().newInstance();
    }
  }

  public static void main( String[] args ) throws Exception {
    newInstance( "/", "com.tutego.insel.lang.ClassToLoadMultipleTimes" );
    newInstance( "/", "com.tutego.insel.lang.ClassToLoadMultipleTimes" );
  }
}