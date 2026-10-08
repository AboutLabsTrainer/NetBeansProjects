# Text- und Zeit-Werkstatt

## Was dieses Beispiel übt

`String` als unveränderliches Objekt (`equals` statt `==`) und die Date-Time-API ohne die alten Klassen `Date` und `Calendar`.

## In dieser Reihenfolge lesen

1. `StringWorkshop` – Länge, Teilstrings, Pool und `equals`
2. `DateTimeWorkshop` – `LocalDate`, `LocalTime`, `LocalDateTime`, `Period`, `Duration`, `DateTimeFormatter`

## Start in NetBeans

Ant-Projekt. Main-Klassen: `de.ibb.workshop.StringWorkshop` und `de.ibb.workshop.DateTimeWorkshop`. Unter Run auswählen und mit F6 starten.

## Ausprobieren

1. Starte `StringWorkshop` und vergleiche die Ausgabe von `==` mit `equals` für zwei gleich geschriebene Texte.
2. Starte `DateTimeWorkshop` und lies das heutige Datum. Die Uhrzeit ändert sich bei jedem Start, das Datum nur um Mitternacht.
3. Suche im Datumsteil die Stelle, an der ein Text mit `DateTimeFormatter` wieder in ein `LocalDate` geparst wird.
