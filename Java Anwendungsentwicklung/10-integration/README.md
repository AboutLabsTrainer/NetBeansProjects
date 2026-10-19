# 10 – Integration

Personenanwendung aus `Tag10-integration`: Swing-Oberfläche, Service und JPA. EclipseLink, Jakarta Persistence 3.2, `RESOURCE_LOCAL` und FlatLaf bleiben im Codebeispiel.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie prüfen und normalisieren eine Person mit denselben Regeln wie `PersonService`, ohne Fenster und ohne Datenbank.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.integration.PersonenApp`.

```bash
mvn -q -DskipTests compile
```

Das Fenster und MySQL (`jae_tag10_jpa`) braucht nur der Start, nicht das Kompilieren. `PersonService.validate` prüft Name, Alter und E-Mail, danach wird die E-Mail klein und ohne Rand geschrieben. Eine leere Suche lädt alle Personen.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `PersonPruefung`.

Dieselben Meldungen wie `validate`: leerer Name, Name über 100 Zeichen, Alter außerhalb 0 bis 150, E-Mail ohne `@`. `null` als Alter und eine leere E-Mail sind erlaubt.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `ServiceRegeln`.

`normalisiereEmail` wie vor dem Speichern. `alleLaden` ist wahr, wenn der Suchbegriff fehlt oder nur aus Leerzeichen besteht.

## Abnahme

- Ein leerer Name wirft `Name darf nicht leer sein!`.
- `  Ada@IBB.de ` wird `ada@ibb.de`. Ein leerer Suchbegriff lädt alle.
- Codebeispiel kompiliert ohne Anzeige und ohne MySQL. Lösungen: `mvn -q test`.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
