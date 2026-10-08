package com.tutego.insel.game.c.vq;

class RandomGameEvents {
  private RandomGameEvents() {}
  static Event next() {
    double random = Math.random();
    if ( random < 0.5 ) return new NoopEvent();
    if ( random < 0.8 ) return new Gift();
    if ( random < 0.9 ) return new Eating();
    return new Mugging();
  }
}
