package com.tutego.insel.game.c.vl;

public class PatternMatchingSwitchWhen {
  public static void main( String[] args ) {
    Event event = new Nap();
    switch ( event ) {
      case Nap nap when nap.duration < 10 ->
          System.out.println( "Too brief a sleep, not worth it." );
      case Nap nap when nap.duration > 100 -> System.out.println( "That's too long, wake up." );
      case Nap nap -> System.out.println( "Recharge and renew with every sleep" );
      case Workout workout -> System.out.println( "Elevate your fitness game" );
      default -> {
      }
    }
  }
}
