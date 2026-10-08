package com.tutego.insel.util.queue;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.regex.Pattern;

enum ImportanceComparator implements Comparator<String> {

  INSTANCE;

  final Predicate<String> IMPORTANT_PATTERN =
          Pattern.compile( "(?i)(wichtig|important|sofort)" ).asPredicate();

  @Override public int compare( String s1, String s2 ) {
    
    boolean isS1Important = IMPORTANT_PATTERN.test( s1 ),
            isS2Important = IMPORTANT_PATTERN.test( s2 );

    // Wenn beide Strings wichtig oder beide Strings unwichtig sind,
    // dann ist die Rückgabe 0.
    if ( isS1Important == isS2Important )
      return 0;    // wichtigkeit(s1) == wichtigkeit(s2)

    // Andernfalls ist einer der Strings wichtig und der andere unwichtig
    if ( isS1Important )
      return -1;   // wichtigkeit(s1) > wichtigkeit(s2) -> s1 < s2
    // else if ( isS2Important )
    return +1;     // wichtigkeit(s2) > wichtigkeit(s1) -> s1 > s2
  }
}