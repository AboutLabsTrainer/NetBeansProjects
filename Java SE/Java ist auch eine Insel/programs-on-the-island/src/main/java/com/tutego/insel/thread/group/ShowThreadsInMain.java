package com.tutego.insel.thread.group;

import java.util.Arrays;
import java.util.Objects;

public class ShowThreadsInMain {
  public static void main( String[] args ) {
    ThreadGroup top = Thread.currentThread().getThreadGroup();
    while ( top.getParent() != null )
      top = top.getParent();

    showGroupInfo( top, "  " );
  }

  public static void showGroupInfo( ThreadGroup group, String indent ) {
    Thread[] threads = new Thread[ group.activeCount() ];
    group.enumerate( threads, false );
    System.out.println( group );

    Arrays.stream( threads )
          .filter( Objects::nonNull )
          .map( t -> String.format("%s%s -> %s ist %sDaemon-Thread",
                                   indent, group.getName(), t,
                                   t.isDaemon() ? "" : "kein ") )
          .forEach( System.out::println );

    ThreadGroup[] activeGroup = new ThreadGroup[ group.activeGroupCount() ];
    group.enumerate( activeGroup, false );

    for ( ThreadGroup g : activeGroup )
      showGroupInfo( g, indent + indent );
  }
}
