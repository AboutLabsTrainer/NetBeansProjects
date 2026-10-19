package com.tutego.insel.bundle;

import java.text.DateFormatSymbols;
import java.util.ListResourceBundle;
import java.util.Locale;

public class MonthResourceBundle_de_DE extends ListResourceBundle {

  private static final String[] MONTHS = new DateFormatSymbols( Locale.GERMAN ).getShortMonths();

  private static final Object[][] contents = {
    { "jan", MONTHS[0] },
    { "month", MONTHS }
  };

  @Override
  protected Object[][] getContents() {
    return contents;
  }
}