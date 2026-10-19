package com.tutego.insel.jdbc;

import java.sql.*;

public class _TableMetaData {
  public static void main( String[] args ) throws Exception {
    String url = "jdbc:h2:file:~/TutegoDB";
    String sql = "SELECT * FROM Customer";
    try ( Connection con = DriverManager.getConnection( url, "sa", "" );
          Statement stmt = con.createStatement();
          ResultSet rs = con.createStatement().executeQuery( sql ) ) {
      ResultSetMetaData meta = rs.getMetaData();

      int numerics = 0;

      for ( int i = 1; i <= meta.getColumnCount(); i++ ) {
        System.out.printf( "%-20s %-20s%n", meta.getColumnLabel( i ),
                                            meta.getColumnTypeName( i ) );
        if ( meta.isSigned( i ) )
          numerics++;
      }

      System.out.println();
      System.out.println( "Spalten: " + meta.getColumnCount() +
                          ", Numerisch: " + numerics );
    }
  }
}
