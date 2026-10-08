package com.tutego.insel.game.c.vo;

import java.time.Duration;

public class Event {
  public int duration;

  /**
   * @return a string representation of this event duration using ISO-8601.
   */
  public String format() {
//  public final String format() {
    return Duration.ofMinutes( duration ).toString();
  }
}
