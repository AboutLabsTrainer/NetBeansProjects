# DI und AOP

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein Aspekt zählt Aufrufe einer annotierten Methode, ohne den Service selbst zu ändern.

## Codebeispiel

Main-Klasse: `com.example.helloworldapi.HelloWorldApiApplication`

`server.port`: **8096** (Profil `dev` und `prod`)

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`Greeter.hello` ist mit `@Counted` markiert. Zwei Aufrufe erhöhen den Zähler auf 2.

Port **8210**. Es gibt keinen HTTP-Endpunkt; der Zähler steckt im Aspekt.

```bash
mvn spring-boot:run
```

## Stufe 2

Eine zweite Annotation `@Audited` an `bye`. Der Audit-Zähler läuft getrennt vom Aufrufzähler.

Port **8211**.

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
