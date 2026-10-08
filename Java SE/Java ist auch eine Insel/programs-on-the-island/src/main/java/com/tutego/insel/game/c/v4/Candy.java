package com.tutego.insel.game.c.v4;

class Candy {
  int price;
  int quantity = 1;

  int totalPrice() {
    int price = quantity * this.price;
    return price;
  }
}
