package com.tutego.insel.lang.invoke;

import java.awt.Rectangle;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodTypeMethodHandleDemo {
  
  public static void main( String[] args ) throws Throwable {
    
    Object rect1 = new Rectangle( 10, 10, 10, 10 );
    String methodName = "union";  
    Class<?> resultType = Rectangle.class;
    Object rect2 = new Rectangle( 20, 20, 100, 100 );
    Class<?> parameterType = rect2.getClass();
    
    MethodType mt = MethodType.methodType( resultType, parameterType );
    MethodHandle methodHandle = MethodHandles.lookup().findVirtual( rect1.getClass(), methodName, mt );
    System.out.println( methodHandle.invoke( rect1, rect2 ) );
  }
}

