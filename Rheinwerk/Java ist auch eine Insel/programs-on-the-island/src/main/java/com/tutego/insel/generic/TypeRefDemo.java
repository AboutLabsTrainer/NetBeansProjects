package com.tutego.insel.generic;

public class TypeRefDemo {
  
  public static void main( String[] args ) {
    TypeRef<Rocket<String>> ref1 = new TypeRef<>(){};
    System.out.println( ref1.type );     // com.tutego.insel.generics.Rocket<java.lang.String>
    TypeRef<Rocket<Byte>> ref2 = new TypeRef<>(){};
    System.out.println( ref2.type );     // com.tutego.insel.generics.Rocket<java.lang.Byte>
  }
}
