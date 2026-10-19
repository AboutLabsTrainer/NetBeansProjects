//package com.tutego.insel.io.ser;
//
//import java.awt.Point;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputFilter;
//import java.io.ObjectInputFilter.Status;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import javax.swing.JLabel;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import org.w3c.dom.Document;
//
//public class _ObjectInputFilterDemo {
//  public static void main( String[] args ) throws IOException, ClassNotFoundException, ParserConfigurationException {
//    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//    try ( ObjectOutputStream oos = new ObjectOutputStream( bos ) ) {
//      oos.writeObject( new Point(10, 20) );
//      oos.writeObject( new byte[1000] );
//      oos.writeObject( new JLabel() );
//      oos.writeObject( DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument() );
//    }
//    
//    ObjectInputStream ois = new ObjectInputStream( new ByteArrayInputStream( bos.toByteArray() ) );
//    ois.setObjectInputFilter( filterInfo -> {
//    
//      ObjectInputFilter serialFilter = ObjectInputFilter.Config.getSerialFilter();
//      if ( serialFilter != null ) {
//        ObjectInputFilter.Status status = serialFilter.checkInput( filterInfo );
//        if ( status != ObjectInputFilter.Status.UNDECIDED )
//          return status;
//      }
//    
//      System.out.printf( "depth=%s, references=%s, serialClass=%s, arrayLength=%s, streamBytes=%s%n",
//                         filterInfo.depth(), filterInfo.references(), 
//                         filterInfo.serialClass(), filterInfo.arrayLength(), filterInfo.streamBytes() );
//    
//      if ( Document.class.isAssignableFrom( filterInfo.serialClass() ) )
//        return Status.REJECTED;
//    
//      return Status.ALLOWED;
//    } );
//    
//    for ( int i = 0; i < 4; i++ )
//      System.out.printf( "%d. gelesenes Objekt: %s%n%n", i + 1, ois.readObject() );
//  }
//}
