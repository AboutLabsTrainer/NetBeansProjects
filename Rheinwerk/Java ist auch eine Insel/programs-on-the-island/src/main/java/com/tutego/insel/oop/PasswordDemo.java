package com.tutego.insel.oop;
public class PasswordDemo {

  public static void main( String[] args ) {
    Password pwd = new Password();
    pwd.assign( "", "earlyAlzheimers" );
    pwd.assign( "earlyAlzheimers", "Z1ON0101" );
    pwd.assign( "Z1ON0101", "P@$$w0rd" );

// System.out.println( pwd.password );   // The field Password.password is not visible
  }
}

