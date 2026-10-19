package com.tutego.insel.game.c.vn;

class Event {
  String about;
  int    duration;

  @Override public String toString() {
    return String.format( "%s[about=%s, duration=%d]",
                          getClass().getSimpleName(),
                          about, duration );
  }
}
