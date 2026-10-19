package com.tutego.insel.meta;

import java.lang.reflect.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ShowMethods {
  public static void main( String[] args ) {
    showMethods( java.awt.Color.BLACK );
  }

  static void showMethods( Object o ) {
    for ( Method method : o.getClass().getMethods() ) {
      System.out.print( method.getReturnType().getSimpleName() + " ");
      System.out.print( method.getName() );
      System.out.print( Stream.of( method.getParameterTypes() )
                              .map( Class::getSimpleName )
                              .collect( Collectors.joining(", ", "(", ")") ) );
       String throwsString = Stream.of( method.getExceptionTypes() )
                                   .map( Class::getSimpleName )
                                   .collect( Collectors.joining(", ") );
       if ( ! throwsString.isEmpty() ) 
         System.out.print( " throws " + throwsString );
       System.out.println();
    }
  }
}