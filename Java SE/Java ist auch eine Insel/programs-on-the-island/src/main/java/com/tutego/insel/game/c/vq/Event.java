package com.tutego.insel.game.c.vq;

abstract class Event {
  String about;
  int    duration;

  abstract void process( Player player );
}
