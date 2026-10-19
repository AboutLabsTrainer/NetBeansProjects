package com.tutego.insel.object;

import java.awt.Font;

public class SuperFont extends Font {
  
  public SuperFont( String name, int style, int size ) {
    super( name, style, size );
  }

  @SuppressWarnings( "deprecation" )
  @Override
  protected void finalize() throws Throwable {
    try {
      // ...
    }
    finally {
      super.finalize();
    }
  }
}