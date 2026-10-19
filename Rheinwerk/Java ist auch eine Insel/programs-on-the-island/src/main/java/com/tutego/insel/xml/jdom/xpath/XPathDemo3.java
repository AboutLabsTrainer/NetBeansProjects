package com.tutego.insel.xml.jdom.xpath;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class XPathDemo3 {
  public static void main( String[] args ) throws Exception {
    Document doc = new SAXBuilder().build( "party.xml" );
    XPathExpression<Attribute> xpath = XPathFactory.instance().compile( "/party/gast[1]/@name", Filters.attribute() );
    System.out.println( xpath.evaluateFirst( doc ).getValue() );
  }
}