package com.tutego.insel.lambda;

import java.util.Arrays;

class StringUtils {
  public static int trimCompare( String s1, String s2 ) {
    return s1.trim().compareTo( s2.trim() );
  }     
}

public class TrimCompareWithDelegation {
  public static void main( String[] args ) {
    String[] words = { "A", "B", "a" };
    Arrays.sort( words,
                 (String s1, String s2) -> StringUtils.trimCompare(s1, s2) );
    System.out.println( Arrays.toString( words ) );
  }
}
