package com.tutego.insel.nio2;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;

public class FileStoreDemo {
  public static void main( String[] args ) throws IOException {
    for ( FileStore store : FileSystems.getDefault().getFileStores() ) {
      long total     = store.getTotalSpace() >> 30;
      long unallocated = store.getUnallocatedSpace() >> 30;
      // long available = store.getUsableSpace() >> 30;
    
      System.out.printf( "%s hat %d GiB Speicherkapazität, %d GiB freien Speicher, Typ %s, Blockgröße %d Byte%n",
                         store, total, unallocated, store.type(), store.getBlockSize() );
    }
  }
}