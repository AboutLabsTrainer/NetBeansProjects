package com.tutego.insel.json;

import java.util.List;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

public class JsonbDemo {

  public static class EvilLaboratory {
    public String from;
    public double volume;
    public boolean didPayElectricityBill;
    public List<String> items;
  }

  public static void main( String[] args ) {

    EvilLaboratory lab1 = new EvilLaboratory();
    lab1.from = "Frank";
    lab1.didPayElectricityBill = true;
    lab1.volume = 12442.33;
    lab1.items = List.of( "corpses", "animals" );

    Jsonb jsonbuilder = JsonbBuilder.create();
    String jsonString = jsonbuilder.toJson( lab1 );
    System.out.println( jsonString );

    EvilLaboratory lab2 = jsonbuilder.fromJson( jsonString, EvilLaboratory.class );
    System.out.printf( "from=%s, volume=%s, didPayElectricityBill=%s, items=%s",
                       lab2.from, lab2.volume, lab2.didPayElectricityBill, lab2.items );
  }
}
