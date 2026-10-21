# 08 Pools

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Die DataSource kommt aus dem Payara-Pool. Die Suche bindet den Begriff als Parameter, nicht als zusammengesetztes SQL.

## Codebeispiel

- Context-Pfad: `/Tag9-ConnectionPools`
- URL: http://localhost:8080/Tag9-ConnectionPools/
- Deploy: Ordner `codebeispiel` auf Payara 6. JNDI `jdbc/ibb`, Datenbank `ibb-db`, Tabelle `jwebb08_products`. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`SearchPattern.JNDI` ist `jdbc/ibb`. `like` setzt den Suchbegriff zwischen zwei Prozentzeichen.

## Stufe 2

Ein fehlender oder leerer Begriff ergibt nur `%`, also alle Zeilen. Ein gesetzter Begriff wird vor dem Einsetzen getrimmt.

## Abnahme

- Stufe 1: `Stift` wird `%Stift%`.
- Stufe 2: `null` und Leerzeichen werden `%`, ` Stift ` wird `%Stift%`.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
