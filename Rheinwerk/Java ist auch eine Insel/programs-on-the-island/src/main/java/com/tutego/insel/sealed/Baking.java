package com.tutego.insel.sealed;

public class Baking {
  static Result cake() {
    return Math.random() > 0.5 ? new Success( "Yummy" )
                               : new Failure( 29, "Burned" );
  }
}
