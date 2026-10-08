package com.tutego.insel.printer;

import java.time.LocalDate;
import java.time.format.*;

public class DatePrinter {
  public static void printCurrentDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate( FormatStyle.MEDIUM );
    System.out.println( LocalDate.now().format( formatter ) );
  }
}