package com.tutego.insel.oop;
class Joystick {

  int numberOfButtons;
  
  Joystick() {
    numberOfButtons = 6;
  }

  Joystick( int numberOfButtons ) {
    numberOfButtons = 6;
    this.numberOfButtons = numberOfButtons;
  }

  Joystick( String producer ) {
    numberOfButtons = 6;
  }
}
