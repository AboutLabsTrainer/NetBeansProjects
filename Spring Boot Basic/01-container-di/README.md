# Container und DI

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Der Container erzeugt eine Bean und injiziert sie über den Konstruktor. Eine zweite Implementierung wird per Qualifier gewählt.

## Codebeispiel

Main-Klasse: `de.ibb.springbootbasic.SpringBootBasicTag2Application`

`server.port`: **8081**

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`GreetingService` hat eine Implementierung. `GET /api/greet?name=Ada` antwortet mit `Hallo Ada`.

Port **8202**.

```bash
mvn spring-boot:run
```

## Stufe 2

Zweite Implementierung `englishGreeting`. `GET /api/greet/en?name=Ada` antwortet mit `Hello Ada`. Der deutsche Endpunkt bleibt.

Port **8203**.

## Abnahme

Im Ordner `uebung-stufe-1` und `uebung-stufe-2`:

```bash
mvn -q -DskipTests compile
```

Im Ordner `loesung/stufe-1` und `loesung/stufe-2`:

```bash
mvn -q test
```

## Lösung erst nach eigenem Versuch

Die Lösungen liegen unter `loesung/stufe-1` und `loesung/stufe-2`. Erst öffnen, wenn die eigene Übung kompiliert und das erwartete Verhalten zeigt.
