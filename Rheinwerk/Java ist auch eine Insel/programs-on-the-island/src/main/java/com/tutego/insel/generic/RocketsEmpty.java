package com.tutego.insel.generic;

public class RocketsEmpty {
  
  public static boolean isOneRocketEmpty( Rocket<?>... rockets ) {
    for ( Rocket<?> rocket : rockets )
      if ( rocket.isEmpty() )
        return true;

    return false;
  }

  public static void main( String[] args ) {
    Rocket<String>  r1 = new Rocket<>( "Bad-Bank" );
    Rocket<Integer> r2 = new Rocket<>( 1500000 );
    System.out.println( isOneRocketEmpty( r1, r2 ));                     // false 
    System.out.println( isOneRocketEmpty( r1, r2, new Rocket<Byte>() )); // true 
  }
}
