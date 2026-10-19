package de.ibb.beans;
import java.io.Serializable;

/**
 * JavaBean mit Zähler für die Session-Scope-Übung der JSP-Actions.
 */
public class CounterBean implements Serializable {
  private int count=0;
  public CounterBean(){}
  public int getCount(){return count;} public void setCount(int v){count=v;}
  public int increment(){return ++count;}
}
