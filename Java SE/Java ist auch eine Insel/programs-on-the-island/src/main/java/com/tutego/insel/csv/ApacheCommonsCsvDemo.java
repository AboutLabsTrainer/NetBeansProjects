package com.tutego.insel.csv;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Locale;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class ApacheCommonsCsvDemo {

  public static void main( String[] args ) throws IOException {

    StringBuilder appendable = new StringBuilder();
    
    CSVFormat csvFormat = CSVFormat.EXCEL.withHeader( "DisplayName", "ISO3Language" );
    try ( CSVPrinter csvPrinter = new CSVPrinter( appendable, csvFormat ) ) {
      for ( Locale l : Arrays.copyOf( Locale.getAvailableLocales(), 5 ) )
        csvPrinter.printRecord( l.getDisplayName(), l.getISO3Language() );
    }

    System.out.println( appendable );
   
    try ( StringReader reader = new StringReader( appendable.toString() );
          CSVParser csvParser = new CSVParser( reader, CSVFormat.EXCEL.withFirstRecordAsHeader() ) ) {
      for ( CSVRecord csvRecord : csvParser )
        System.out.printf( "Record=%d - %s, %s%n",
                           csvRecord.getRecordNumber(),
                           csvRecord.get( 0 ), csvRecord.get( 1 ) );
    
    //      csvParser.getRecords().stream().forEach( csvRecord -> {
    //        System.out.println( csvRecord );
    //      } );
    }
  }
}
