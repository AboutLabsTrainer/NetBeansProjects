package com.tutego.insel.math;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundWithSetScale {
  public static void main( String[] args ) {
    BigDecimal petrol = new BigDecimal( "1.399" ).multiply( new BigDecimal( 45 ) );
    System.out.println( petrol.setScale( 3, RoundingMode.HALF_UP ) );
    System.out.println( petrol.setScale( 2, RoundingMode.HALF_UP ) );
  }
}
