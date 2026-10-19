package com.tutego.insel.game.c.v3;

class Candy {
  String name;
  int    price;
  int    quantity = 1;

  int totalPrice() {
    return price * quantity;
  }

  void resetQuantity() {
    quantity = 1;
  }
}
