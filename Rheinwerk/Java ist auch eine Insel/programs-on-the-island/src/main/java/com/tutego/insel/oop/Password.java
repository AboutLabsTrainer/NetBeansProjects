package com.tutego.insel.oop;

public class Password {

  private String password = "";

  public void assign( String oldPassword, String newPassword ) {
    if ( password.equals(oldPassword) && newPassword != null ) {
      password = newPassword;

      System.out.println( "Password set" );
    }
    else
      System.out.println( "Password could not be set" );
  }

  public boolean check( String passwordToCheck ) {
    return password.equals( passwordToCheck );
  }
}