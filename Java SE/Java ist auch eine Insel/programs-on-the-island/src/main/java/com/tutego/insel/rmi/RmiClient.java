package com.tutego.insel.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {
  public static void main( String[] args ) throws RemoteException, NotBoundException {
    Registry registry = LocateRegistry.getRegistry();
    Adder adder = (Adder) registry.lookup( Adder.SERVICE_NAME  );
    System.out.println( adder.add( 47, 11 ) );
  }
}
