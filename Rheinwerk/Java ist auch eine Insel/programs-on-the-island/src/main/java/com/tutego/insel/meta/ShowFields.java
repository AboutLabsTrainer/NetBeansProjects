package com.tutego.insel.meta;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class ShowFields {
  public static void main( String[] args ) {
    Class<?> c = java.awt.geom.Rectangle2D.Double.class;
    System.out.println( "class " + c.getCanonicalName() + " {" );
    for ( Field publicField : c.getFields() ) {
      String mods = Modifier.toString( publicField.getModifiers() );
      String type = publicField.getType().getName();
      String name = publicField.getName();
      System.out.printf( "  %s %s %s;%n", mods, type, name);
    //      System.out.println( "  " + publicField + ";" );
    }
    System.out.println( "}" );
  }
}
