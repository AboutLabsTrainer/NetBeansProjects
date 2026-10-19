package com.tutego.insel.generic;

public class RocketPlayer {
  
  @SuppressWarnings("unused")
  public static void main( String[] args ) {
    Rocket<Integer>  intRocket     = new Rocket<Integer>();
    Rocket<String>   stringRocket  = new Rocket<String>();
    
    intRocket.set( 1 );
    int x = intRocket.get();
    
    stringRocket.set( "Sesquipedalian" );
    String s = stringRocket.get();

    Rocket<Rocket<String>> rocketOfRockets = new Rocket<Rocket<String>>();
    rocketOfRockets.set( new Rocket<String>() );
    rocketOfRockets.get().set( "Inner Rocket<String>" );
    System.out.println( rocketOfRockets.get().get() ); // Inner Rocket<String>
  }    
}