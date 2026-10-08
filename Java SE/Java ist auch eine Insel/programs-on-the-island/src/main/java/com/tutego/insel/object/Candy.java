package com.tutego.insel.object;

public class Candy {

  int price;
  int quantity;

  @Override
  public boolean equals( Object o ) {
    if ( o == null )
      return false;

    if ( o == this )
      return true;

    if ( o.getClass() != getClass() )
      return false;

    Candy that = (Candy) o;

    return    this.price == that.price
           && this.quantity == that.quantity;
  }

  @Override
  public int hashCode() {
    return 31 * price + quantity;
  }
}