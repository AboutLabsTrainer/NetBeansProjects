package com.tutego.insel.lambda;

import java.util.function.Predicate;

class InnerVsLambdaDefaultMethod {
  public static void main( String[] args ) {
    @SuppressWarnings( "unused" )
    class TruePredicate implements Predicate<Object> {
      @Override public boolean test( Object o ) {
        return negate().test( o );
      }
    }
    // Predicate<Object> truePredicate = o -> negate().test( o );
  }
}
