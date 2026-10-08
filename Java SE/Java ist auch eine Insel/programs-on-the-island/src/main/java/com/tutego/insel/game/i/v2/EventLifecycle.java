package com.tutego.insel.game.i.v2;

interface EventLifecycle {
  void start();
  void finish();
  default void pause() { }
}
