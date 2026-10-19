# 01 Sets und Maps

IDE und Maven: [../../../docs/ide-maven.md](../../../docs/ide-maven.md)

## Lernziel

Ein Set merkt sich einen Wert nur einmal. Eine Map zählt, wie oft ein Wort vorkommt. In der zweiten Stufe hängt an jedem Kurs ein Set von Namen, und Sie können die Kurse einer Person wiederfinden.

## Codebeispiel

Quiz, Städte-Set, Länder-Map, `equals`/`hashCode`, Worthäufigkeit, LRU-Cache und `TreeSet`.

Main-Klasse: `de.ibb.setsmapschallenge.Main`

Im Ordner `codebeispiel`: `mvn exec:java`

## Stufe 1

`de.ibb.setsmaps.Sammlungen`

- `staedte` nimmt eine Liste von Namen und liefert ein Set. `Berlin`, `Hamburg`, `Berlin` ergibt die Menge `{Berlin, Hamburg}`.
- `wortHaeufigkeit` zählt Wörter: Kleinbuchstaben, die Zeichen `.,!?;:` entfernen, an Leerzeichen trennen, leere Stücke weglassen, mit `merge` zählen.

Abnahme für den Text `Java ist eine Programmiersprache. Java ist objektorientiert.`: `java` = 2, `ist` = 2, `eine` = 1, `programmiersprache` = 1, `objektorientiert` = 1.

## Stufe 2

`KursBelegung` speichert Kurse auf Mengen von Studierenden. `anmelden` liefert `false`, wenn derselbe Name in diesem Kurs schon steht. `teilnehmer` liefert eine Kopie der Menge, bei einem unbekannten Kurs die leere Menge. `kurseVon` liefert alle Kurse, in denen der Name steht.

Abnahme: `Java/Mia`, noch einmal `Java/Mia` ist `false`, `Java/Leo` und `SQL/Mia` kommen dazu. Teilnehmer von Java sind `{Mia, Leo}`. Kurse von Mia sind `{Java, SQL}`. Kurse von Leo sind `{Java}`.

## Abnahme

Die Mengen und Zähler oben. Gerüste: `mvn -q -DskipTests compile`, ohne Testklassen.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2`. Prüfen mit `mvn -q test`.
