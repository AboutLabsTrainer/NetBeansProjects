package com.tutego.insel.game.vxxxx;

import java.util.ArrayList;

public class Candies {
  private final ArrayList<Candy> candies = new ArrayList<Candy>();

  public void add( Candy newCandy ) {
    candies.add( newCandy );
  }

  public boolean remove( String name ) {
    for ( int i = 0; i < candies.size(); i++ )
      if ( candies.get( i ).name.equals( name ) ) {
        candies.remove( i );
        return true;
      }
    return false;
  }

  public void list() {
    for ( Candy candy : candies )
      System.out.println( candy );
  }
}
