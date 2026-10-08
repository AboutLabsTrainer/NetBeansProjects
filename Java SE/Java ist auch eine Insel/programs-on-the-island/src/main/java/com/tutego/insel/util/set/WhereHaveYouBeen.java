package com.tutego.insel.util.set;

import java.text.BreakIterator;
import java.util.*;

public class WhereHaveYouBeen {
    public static void main( String[] args ) {
    // Menge mit Städten aufbauen
    
    Set<String> allCities = new HashSet<>();
    allCities.add( "Sonsbeck" );
    allCities.add( "Düsseldorf" );
    allCities.add( "Manila" );
    allCities.add( "Seol" );
    allCities.add( "Siquijor" );

    // Menge für besuchte Städte aufbauen

    Set<String> visitedCities = new TreeSet<>();

    // Satz parsen und in Wörter zerlegen. Alle gefundenen Städte
    // in neue Datenstruktur aufnehmen
    
    String sentence = "From Sonsbeck I drive to Düsseldorf and fly to Manila.";
    BreakIterator iter = BreakIterator.getWordInstance();
    iter.setText( sentence );

    for ( int first = iter.first(), last = iter.next();
          last != BreakIterator.DONE;
          first = last, last = iter.next() ) {
      String word = sentence.substring( first, last );
      if ( allCities.contains( word ) )
        visitedCities.add( word );
    }

    // Kleine Statistik

    System.out.println( "Number of visited cities: " + visitedCities.size() );
    System.out.println( "Number of unvisited cities: " +
                        (allCities.size() - visitedCities.size()) );
    System.out.println( "Visited cities: " + String.join( ", ", visitedCities ) );
    Set<String> unvisitedCities = new TreeSet<>( allCities );
    unvisitedCities.removeAll( visitedCities );
    System.out.println( "Unvisited cities: " + String.join( ", ", unvisitedCities ) );
  }
}