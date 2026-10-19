package com.tutego.insel.util.list;

import java.util.*;

public class MemorizeYourRoadTripRoute {
  @SuppressWarnings( "resource" )
  public static void main( String[] args ) {
    List<String> cities = new ArrayList<>();

    while ( true ) {
      System.out.println( "What new city should be added?" );
      String newCity = new Scanner( System.in ).nextLine();
      cities.add( newCity );

      System.out.printf( "What is the overall route? (Hint: %d %s)%n",
                         cities.size(),
                         cities.size() == 1 ? "city" : "cities" );

      for ( String city : cities ) {
        String guess = new Scanner( System.in ).nextLine();
        if ( !city.equalsIgnoreCase( guess ) ) {
          System.out.printf( "%s is not correct, %s would be correct. Too bad!%n",
                             guess, city );
          return;
        }
      }
      System.out.println( "Great, all cities in the right order!" );
    }
  }
}