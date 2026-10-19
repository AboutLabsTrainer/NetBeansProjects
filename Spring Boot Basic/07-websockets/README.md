# WebSockets

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

STOMP nimmt einen Beitritt an und liefert einen Text für `/topic/public`. Der einfache Broker steckt in der Anwendung.

## Codebeispiel

Main-Klasse: `com.example.websockets.WebsocketsChatApplication`

`server.port`: **9088**

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

STOMP-Endpunkt `/ws`, Anwendungspraefix `/app`, Broker `/topic`.

## Stufe 1

`ChatTexts.join` baut `Ada ist dem Chat beigetreten!`. Die Methode hängt an `@MessageMapping("/chat.join")`.

Port **8214**.

```bash
mvn spring-boot:run
```

## Stufe 2

Zusätzlich `GET /api/preview?sender=Ada` mit demselben Text.

Port **8215** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8215/api/preview?sender=Ada`

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
