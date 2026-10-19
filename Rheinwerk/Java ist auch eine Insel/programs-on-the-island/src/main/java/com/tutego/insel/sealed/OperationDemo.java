package com.tutego.insel.sealed;

public class OperationDemo {
  public static void main( String[] args ) {
    Result result = Baking.cake();
//    if ( result instanceof Success )
//      System.out.println( "Success: " + result.body );
//    else
//      System.out.println( "Failure: " + result.body );

    switch ( result ) {
      case Success success -> System.out.println();
      case Failure failure -> System.out.println();
    }
  }
}
