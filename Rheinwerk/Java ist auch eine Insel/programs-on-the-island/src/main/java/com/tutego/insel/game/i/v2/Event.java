package com.tutego.insel.game.i.v2;

abstract class Event implements EventLifecycle {
  String about;
  int duration;

  abstract void process();

  @Override public void start() { }
  @Override public void finish() { }
}
