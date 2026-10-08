package com.tutego.insel.xml.jaxb;

import javax.xml.bind.JAXB;
import com.tutego.insel.xml.jaxb.bible.Bible;

public class BibleOnline {
  public static void main( String[] args ) {
    String url = "http://api.preachingcentral.com/bible.php?passage=Jn3:16&version=schlachter";
    Bible bible = JAXB.unmarshal( url, Bible.class );
    System.out.println( bible.getRange().getItem().getText() );
  }
}