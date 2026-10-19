package com.tutego.insel.cdi;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class StartMain {
  public static void main( String[] args ) {
    SeContainerInitializer initializer = SeContainerInitializer.newInstance();
    try ( SeContainer container = initializer.initialize() ) {
      Application application = container.select( Application.class ).get();
      application.start();
    }
  }
}