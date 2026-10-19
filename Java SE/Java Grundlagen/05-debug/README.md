# Modul 05 – Debugger

## Lernziel

Zwei Rechenfehler finden und korrigieren. Im Codebeispiel bleiben die Fehler absichtlich stehen.

Start in der IDE: [docs/ide-maven.md](../../../docs/ide-maven.md). Geöffnet wird der Unterordner mit der `pom.xml`, nicht dieser Elternordner.

## Codebeispiel

`DebugUebung` soll für 5 das Quadrat 25 und die Summe 15 liefern, rechnet aber anders. Die Methoden nicht schon im Beispiel reparieren. Main-Klasse: `de.ibb.debug.DebugUebung`.

```bash
mvn exec:java
```

## Stufe 1

Ordner `uebung-stufe-1`. In `Rechner` sind `quadrat` und `summeBis` falsch vorgegeben. Quadrat ist Multiplikation. Die Summe läuft von 1 bis einschließlich n.

Abnahme: `quadrat(5)` ist 25. `summeBis(5)` ist 15. `summeBis(1)` ist 1.

## Stufe 2

Ordner `uebung-stufe-2`. `Mittelwert.durchschnitt` teilt ganzzahlig und durch `length + 1`. Der Durchschnitt ist die Summe als `double`, geteilt durch die Länge. Ein leeres Array ergibt 0.

Abnahme: `{5, 8, -3, 10, 42}` ergibt 12.4. Ein leeres Array ergibt 0.

## Lösung

`loesung/stufe-1` und `loesung/stufe-2` erst nach dem eigenen Versuch öffnen. Prüfung: `mvn test`.
