# Caching

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

`@Cacheable` merkt sich das Ergebnis. Ein zweiter Aufruf mit demselben Key trifft den Cache.

## Codebeispiel

Main-Klasse: `com.example.cachingdemo.CachingDemoApplication`

`server.port`: **8080** (nicht gesetzt, Boot-Standard)

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`price` liegt im Cache `prices`. Der zweite Aufruf mit demselben SKU erhöht den Zähler nicht.

Port **8308** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8308/api/prices/A`

## Stufe 2

Zweite Cache-Regel: `tax` im Cache `taxes`, getrennt von `prices`.

Port **8309** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8309/api/taxes/A`

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
