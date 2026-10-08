package com.tutego.insel.string;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchResultDemo {

  public static void main( String[] args ) {
    String s = "Insel: 3836228734, Reguläre Ausdrücke: 3897213494";
    
    Collection<MatchResult> matches = new ArrayList<>();
    
    for ( Matcher m = Pattern.compile( "\\d{9,10}[\\dxX]" ).matcher( s ); m.find(); )
      matches.add( m.toMatchResult() );
    
    matches.forEach( r -> System.out.printf( "%s von %d-%d%n",
                                             r.group(), r.start(), r.end() ) );
  }
}