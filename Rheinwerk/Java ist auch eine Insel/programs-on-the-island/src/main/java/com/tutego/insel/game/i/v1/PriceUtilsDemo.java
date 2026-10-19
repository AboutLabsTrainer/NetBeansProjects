package com.tutego.insel.game.i.v1;

public class PriceUtilsDemo {
  public static void main( String[] args ) {
    Buyable hercules = new Bike();
    Buyable binarium = new MuseumVisit( 8 );
    Buyable winora = new Bike();
    Buyable mimomenta = new MuseumVisit( 12 );
    double sum = PriceUtils.calculateSum( hercules, binarium, winora, mimomenta );
    System.out.println( sum );   // 418.0
  }
}
