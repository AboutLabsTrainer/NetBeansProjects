package com.tutego.insel.nongeneric;

public class PlayerRocketDemo {
  
  public static void main( String[] args ) {
    Player michael = new Player();
    michael.name = "Omar Arnold";
    Rocket rocket = new Rocket();
    Long aBigNumber = 11111111111111L;
    rocket.set( aBigNumber );
    michael.leftRocket  = rocket;
    michael.rightRocket = new Rocket( 2222222222222222222L );
    
    System.out.println( michael.name + " transports " +
                        michael.leftRocket.get() + " and " + michael.rightRocket.get() );
    
    Long val1 = (Long) michael.leftRocket.get();
    Long val2 = (Long) michael.rightRocket.get();
    
    System.out.println( val1.compareTo( val2 ) > 0 ? "left" : "right" );
  }
}