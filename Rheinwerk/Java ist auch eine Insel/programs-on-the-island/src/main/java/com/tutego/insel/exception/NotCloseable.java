package com.tutego.insel.exception;
public class NotCloseable implements AutoCloseable {
  @Override public void close() {
    throw new UnsupportedOperationException( "I do not like close()" );
  }
}