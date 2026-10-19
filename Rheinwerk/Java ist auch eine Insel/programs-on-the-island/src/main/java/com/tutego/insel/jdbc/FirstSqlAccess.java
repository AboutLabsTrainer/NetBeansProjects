package com.tutego.insel.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstSqlAccess {
  public static void main( String[] args ) {

    String url = "jdbc:h2:file:~/TutegoDB";
    try ( Connection con = DriverManager.getConnection( url, "sa", "" );
          Statement stmt = con.createStatement() ) {
    
      if ( ! con.getMetaData().getTables( null, null, "CUSTOMER", null ).next() ) {
        String[] sqlStmts = {
          "CREATE TABLE CUSTOMER(ID INTEGER NOT NULL PRIMARY KEY,FIRSTNAME VARCHAR(255),"
          + "LASTNAME VARCHAR(255),STREET VARCHAR(255),CITY VARCHAR(255))",
          "INSERT INTO CUSTOMER VALUES(0,'Laura','Steel','429 Seventh Av.','Dallas')",
          "INSERT INTO CUSTOMER VALUES(1,'Susanne','King','366 - 20th Ave.','Olten')",
          "INSERT INTO CUSTOMER VALUES(2,'Anne','Miller','20 Upland Pl.','Lyon')" };
        for ( String sql : sqlStmts )
          stmt.executeUpdate( sql );
        System.out.println( "Table created and data inserted." );
      }
    
      try ( ResultSet rs = stmt.executeQuery( "SELECT * FROM CUSTOMER" ) ) {
        while ( rs.next() )
          System.out.printf( "%s, %s %s%n",
                             rs.getString( 1 ), rs.getString( 2 ), rs.getString( 3 ) );
      }
    }
    catch ( SQLException e ) {
      e.printStackTrace();
    }
  }
}