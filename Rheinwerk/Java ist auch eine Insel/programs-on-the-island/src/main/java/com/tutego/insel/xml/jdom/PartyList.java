package com.tutego.insel.xml.jdom;

import java.util.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

class PartyList {
  public static void main( String[] args ) throws Exception {
    Document doc = new SAXBuilder().build( "party.xml" );
    List<Content> partyInfo = doc.getRootElement().getContent();
    for ( Content content : partyInfo )
      System.out.println( content );
  }
}
