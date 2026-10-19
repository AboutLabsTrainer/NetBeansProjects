package com.tutego.insel.nested;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class MinMaxDemo {
  public record MinMax(int min, int max) {}

  public static MinMax minMax( int... values ) {
    IntSummaryStatistics stats = IntStream.of( values ).summaryStatistics();
    return new MinMax( stats.getMin(), stats.getMax() );
  }
}
