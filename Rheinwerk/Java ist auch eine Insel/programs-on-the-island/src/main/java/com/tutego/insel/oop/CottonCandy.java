package com.tutego.insel.oop;

class CottonCandy {

  static         int CALORIES = 630;
  /*non-static*/ int calories = 630;

  CottonCandy() {
    // this( calories );
    this( CALORIES );
  }

  CottonCandy( int calories ) {
  	this.calories = calories;
  }
}