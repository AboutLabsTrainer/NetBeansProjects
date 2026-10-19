package com.tutego.insel.pattern.listener;

import java.util.EventObject;

public class AdEvent extends EventObject {
  
  private final String slogan;

  public AdEvent( Object source, String slogan ) {
    super( source );
    this.slogan = slogan;
  }

  public String getSlogan() {
    return slogan;
  }
}