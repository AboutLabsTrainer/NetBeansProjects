//package com.tutego.insel.xml.dom;
//
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//public class _DOMParty {
//  public static void main( String[] args ) throws Exception {
//    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//    DocumentBuilder builder = factory.newDocumentBuilder();
//    try ( InputStream in = Files.newInputStream( Paths.get( "party.xml" ) ) ) {
//      Document document = builder.parse( in );
//      Element partyRoot = document.getDocumentElement();
//      NodeList guests = partyRoot.getElementsByTagName( "gast" );
//      Node firstGuest = guests.item( 0 );
//      if ( firstGuest.getNodeType() == Node.ELEMENT_NODE ) {
//        NodeList drinks = ((Element) firstGuest).getElementsByTagName( "getraenk" );
//        System.out.println( drinks.item( 0 ).getTextContent() );
//      }
//    }
//  }
//}