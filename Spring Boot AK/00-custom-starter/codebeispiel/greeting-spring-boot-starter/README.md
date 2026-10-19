Die Aufgabe dieses Moduls steht in der [README des Elternordners](../../README.md).

# Greeting Spring Boot Starter

## Was dieses Beispiel übt

Ein eigenes Starter-Modul: typisierte Properties, eine Auto-Configuration und die Registrierung über `AutoConfiguration.imports`. Liegt das Jar auf dem Classpath, stellt Spring Boot einen `GreetingService` bereit, solange die Anwendung keinen eigenen definiert.

## Wichtige Klassen in Lesereihenfolge

1. `GreetingService` – Schnittstelle, die Anwendungen injizieren
2. `GreetingProperties` – Bindung der Schlüssel `greeting.*`
3. `DefaultGreetingService` – Standardimplementierung
4. `GreetingAutoConfiguration` – legt die Bean an, wenn sie fehlt und der Starter aktiv ist

## Start

Dieses Modul ist eine Bibliothek und startet keinen Webserver. In NetBeans die Tests des Moduls ausführen, oder im Modulordner:

```bash
mvn test
```

Die lauffähige Anwendung steht im Modul `greeting-spring-boot-starter-demo` (Port **8101**).

## Ausprobieren

1. `mvn test` in diesem Modul: die Tests prüfen Properties, die erzeugte Bean und dass eine eigene `GreetingService`-Bean die Auto-Configuration ersetzt.
2. Danach die Demo starten und http://localhost:8101/greet/Petra öffnen.
3. In der Demo `greeting.message` oder `greeting.uppercase` ändern, neu starten und denselben Pfad erneut aufrufen.
