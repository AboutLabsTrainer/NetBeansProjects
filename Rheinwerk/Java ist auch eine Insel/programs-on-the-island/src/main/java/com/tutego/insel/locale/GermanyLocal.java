package com.tutego.insel.locale;

import static java.util.Locale.*;
import static java.lang.System.out;

public class GermanyLocal {

  public static void main( String[] args ) {
    out.println(GERMANY.getCountry());           // DE
    out.println(GERMANY.getLanguage());          // de
    out.println(GERMANY.getVariant());           //
    out.println(GERMANY.getISO3Country());       // DEU
    out.println(GERMANY.getISO3Language());      // deu
    out.println(CANADA.getDisplayCountry());     // Kanada
    out.println(GERMANY.getDisplayLanguage());   // Deutsch
    out.println(GERMANY.getDisplayName());       // Deutsch (Deutschland)
    out.println(CANADA.getDisplayName());        // Englisch (Kanada)
    out.println(GERMANY.getDisplayName(FRENCH)); // allemand (Allemagne)
    out.println(CANADA.getDisplayName(FRENCH));  // anglais (Canada)
  }
}
