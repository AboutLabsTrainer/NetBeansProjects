# 05 Beans

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Eine JavaBean hat einen öffentlichen Standardkonstruktor und Paare aus Getter und Setter für `jsp:useBean`.

## Codebeispiel

- Context-Pfad: `/Tag6`
- URL: http://localhost:8080/Tag6/
- Deploy: Ordner `codebeispiel` auf Payara 6. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`PersonBean.getDisplayName` verbindet Vor- und Nachname mit einem Leerzeichen. Ein leerer Teil fällt weg.

## Stufe 2

`hasValidEmail` verlangt genau ein `@`, das nicht am Rand steht. `isAdult` ist wahr ab 18 Jahren.

## Abnahme

- Stufe 1: `Ada Lovelace`, nur Vorname, nur Nachname.
- Stufe 2: `a@b.de` ist gültig, `ab.de` und `@b.de` sind es nicht. 17 ist nicht volljährig, 18 schon.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
