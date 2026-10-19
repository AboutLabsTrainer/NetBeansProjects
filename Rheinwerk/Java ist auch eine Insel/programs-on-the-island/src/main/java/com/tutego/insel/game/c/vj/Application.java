package com.tutego.insel.game.c.vj;

class Application {

  public static void main( String[] args ) {
    Candy cottonCandy  = new Candy();
    Player pillLady    = new Player();
    pillLady.candy     = cottonCandy;
    cottonCandy.player = pillLady;

    Candy babyRuth  = new Candy();
    pillLady.candy  = babyRuth;
  }
}
