package com.tutego.insel.xml.jdom;

import java.io.IOException;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

class AlbertsDrink {
  public static void main( String[] args ) throws JDOMException, IOException  {
    Document doc = new SAXBuilder().build( "party.xml" );

    Element party = doc.getRootElement();
    Element drink = party.getChild( "gast" ).getChild( "getraenk" );
    String  drinkName = drink.getText();
    System.out.println( "getraenk: " + drinkName );

    Element albert = party.getChild( "gast" );
    System.out.println( albert.getChildText( "getraenk" ) );
  }
}