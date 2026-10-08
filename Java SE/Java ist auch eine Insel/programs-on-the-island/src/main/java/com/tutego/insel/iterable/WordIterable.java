package com.tutego.insel.iterable;

import java.util.*;

class WordIterable implements Iterable<String>, Iterator<String> {
  
  private final StringTokenizer tokenizer;

  public WordIterable( String s ) {
    tokenizer = new StringTokenizer( s );
  }

  // Method from interface Iterable

  @Override public Iterator<String> iterator() {
    return this;
  }

  // Methods from interface Iterator

  @Override public boolean hasNext() {
    return tokenizer.hasMoreTokens();
  }

  @Override public String next() {
    if ( hasNext() )
      return tokenizer.nextToken();
    throw new NoSuchElementException();
  }
}