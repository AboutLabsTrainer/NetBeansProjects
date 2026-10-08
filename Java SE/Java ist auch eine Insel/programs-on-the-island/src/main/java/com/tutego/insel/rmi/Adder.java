package com.tutego.insel.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Adder extends Remote {
  String SERVICE_NAME = "com.tutego.insel.rmi.Adder";

  int add( int x, int y ) throws RemoteException;
}
