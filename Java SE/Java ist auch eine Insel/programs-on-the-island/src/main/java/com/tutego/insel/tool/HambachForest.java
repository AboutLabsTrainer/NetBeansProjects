package com.tutego.insel.tool;

import java.awt.*;

public class HambachForest {
  public static void main( String[] args ) {
    boolean rweWantsToCutTrees = false;
    // boolean rweWantsToCutTrees = true;
    Forest hambachForest = new Forest();
    if ( rweWantsToCutTrees ) {
      Protest<Forest> p1 = new Protest<>();
      p1.believeIn = hambachForest;
    }
  }
}

class Forest {}

class Protest<T> {
  static Rectangle MIN_BOUNDARY;
  T believeIn;
  java.time.LocalDate since;
  //  java.time.LocalDate since = LocalDate.now();
}