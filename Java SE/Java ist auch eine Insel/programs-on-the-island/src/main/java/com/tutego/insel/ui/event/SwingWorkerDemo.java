package com.tutego.insel.ui.event;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

public class SwingWorkerDemo extends JFrame {
  private final JButton button = new JButton( "Change my mind!" );

  SwingWorkerDemo() {
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    add( button );
    button.addActionListener( e -> new ClockPrecision().execute() );

    pack();
  }

  class ClockPrecision extends SwingWorker<Long,Object> {
    @Override public Long doInBackground() {
      long startNano = System.nanoTime();
      try {
        TimeUnit.SECONDS.sleep( 2 );
      }
      catch ( InterruptedException e ) { }
      return (System.nanoTime() - startNano) / (1000 * 1000);
    }

    @Override protected void done() {
      try {
        button.setText( "" + get() );
      }
      catch ( InterruptedException | ExecutionException e ) {
        e.printStackTrace();
      }
    }
  }

  public static void main( String[] args ) {
    new SwingWorkerDemo().setVisible( true );
  }
}