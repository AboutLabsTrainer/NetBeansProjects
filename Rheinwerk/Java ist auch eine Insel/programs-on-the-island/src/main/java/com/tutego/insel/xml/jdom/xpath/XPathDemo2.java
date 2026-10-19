package com.tutego.insel.xml.jdom.xpath;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class XPathDemo2 {
  public static void main( String[] args ) throws Exception {
    Document doc = new SAXBuilder().build( "party.xml" );
    XPathExpression<Element> xpath = XPathFactory.instance().compile( "/party/gast/getraenk", Filters.element() );
    for ( Element elem : xpath.evaluate( doc ) )
      System.out.println( elem.getValue() );
  }
}