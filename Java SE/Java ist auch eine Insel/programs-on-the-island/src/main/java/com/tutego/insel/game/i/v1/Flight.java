package com.tutego.insel.game.i.v1;

class Flight extends Event implements Buyable, Comparable<Flight> {

  final double ticketPrice;

  Flight( int ticketPrice ) {this.ticketPrice = ticketPrice;}

  @Override public double price() {
    return ticketPrice;
  }

  @Override public int compareTo( Flight other ) {
    return Double.compare( ticketPrice, other.ticketPrice );
  }
}
