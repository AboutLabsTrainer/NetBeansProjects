# Scopes

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein Singleton zählt weiter. Ein Prototype fängt bei jedem `getBean` neu an. Der Request-Scope gilt für einen HTTP-Request.

## Codebeispiel

Main-Klasse: `de.ibb.scopes.SpringScopesApplication`

`server.port`: **9097**

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`GET /api/scopes` liefert beim ersten Aufruf `singleton=1,prototype=1` und beim zweiten `singleton=2,prototype=1`.

Port **8212** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8212/api/scopes`

## Stufe 2

Dritter Zähler im Request-Scope. Beide Aufrufe zeigen `request=1`, der Singleton läuft weiter.

Port **8213** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8213/api/scopes`

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
