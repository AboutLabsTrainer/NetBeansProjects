package com.tutego.insel.game.i.v1;

class PriceUtils {
  static double calculateSum( Buyable first, Buyable... more ) {
    double result = first.price();
    for ( Buyable buyable : more )
      result += buyable.price();
    return result;
  }
}
