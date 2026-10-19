package com.tutego.insel.game.c.vk;

class Application {

  public static void main( String[] args ) {
    Player reese = new Player();
    reese.buy( new Candy( "Kitty Katty", 200 ) );
    reese.buy( new Candy( "P&Ps", 250 ) );
    reese.buy( new Candy( "Snackers", 300 ) );
    reese.buy( new Candy( "Snackers", 300 ) );
    reese.buy( new Candy( "Bubba Hubba", 350 ) );
    reese.listCandies();
    System.out.println( reese.eat( "Chick-o-Stick" ) );
    System.out.println( reese.eat( "Kitty Katty" ) );
    reese.listCandies();
    reese.buy( new Candy( "Kitty Katty", 200 ) );
    reese.buy( new Candy( "Kitty Katty", 200 ) );
    System.out.println( reese.eat( "Kitty Katty" ) );
    reese.listCandies();
  }
}
