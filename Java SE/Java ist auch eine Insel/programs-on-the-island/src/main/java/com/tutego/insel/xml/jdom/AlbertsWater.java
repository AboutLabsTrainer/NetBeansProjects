package com.tutego.insel.xml.jdom;

import java.util.List;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

class AlbertsWater {
  public static void main( String[] args ) throws Exception {
    Document doc = new SAXBuilder().build( "party.xml" );
    Element party = doc.getRootElement();
    Element albert = party.getChild( "gast" );

    List<Content> albertInfo = albert.getContent();
    Element drink = new Element( "getraenk" );
    drink.addContent( "Wasser" );

    albertInfo.add( drink );

    XMLOutputter fmt = new XMLOutputter();
    fmt.output( doc, System.out );
  }
}