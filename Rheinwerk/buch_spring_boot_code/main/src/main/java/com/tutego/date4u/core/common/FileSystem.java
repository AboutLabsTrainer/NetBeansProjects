package com.tutego.date4u.core.common;

public interface FileSystem {
  long getFreeDiskSpace();
  byte[] load( String filename );
  void store( String filename, byte[] bytes );
}
