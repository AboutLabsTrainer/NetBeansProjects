package com.tutego.insel.lambda;
import java.util.*;

public class TrimCompare {
  public static void main( String[] args ) {
    class TrimComparator implements Comparator<String> {
      @Override
      public int compare( String s1, String s2 ) {
        return s1.trim().compareTo( s2.trim() );
      }
    }
    String[] words = { "M", "\nSkyfall", " Q", "\t\tAdele\t" };
    Arrays.sort( words, new TrimComparator() );
    System.out.println( Arrays.toString( words ) );
  }
}