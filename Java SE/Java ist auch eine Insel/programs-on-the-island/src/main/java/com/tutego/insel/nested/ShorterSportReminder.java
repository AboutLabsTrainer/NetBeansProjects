package com.tutego.insel.nested;

import java.util.*;

public class ShorterSportReminder {
  public static void main( String[] args ) {
    new Timer().scheduleAtFixedRate( new TimerTask() {
                                       @Override public void run() {
                                         System.out.println( "Come on, you candyass" );
                                       }
                                     },
                                     0 /* ms delay */,
                                     1000 /* ms period */);
  }
}