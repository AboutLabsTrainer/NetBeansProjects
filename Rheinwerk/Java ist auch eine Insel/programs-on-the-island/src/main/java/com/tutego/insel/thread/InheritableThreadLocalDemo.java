package com.tutego.insel.thread;

import java.util.concurrent.ThreadLocalRandom;

public class InheritableThreadLocalDemo {
  public static void main( String[] args ) throws InterruptedException {
    new InheritingThread().start();
    Thread.sleep( 50 );
    System.exit( 0 );
  }
}

class InheritingThread extends Thread {
  // private static final ThreadLocal<String> mem = new ThreadLocal<>();
  private static final InheritableThreadLocal<Long> mem = new InheritableThreadLocal<>();

  @Override
  public void run() {
    long rnd = ThreadLocalRandom.current().nextLong();
    System.out.println( Thread.currentThread() + " has " + mem.get() + " is getting " + rnd );
    mem.set( rnd );

    new InheritingThread().start();
  }
}
