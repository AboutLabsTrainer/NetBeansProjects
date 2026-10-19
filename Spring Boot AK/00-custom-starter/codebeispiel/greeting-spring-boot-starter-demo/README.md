Die Aufgabe dieses Moduls steht in der [README des Elternordners](../../README.md).

# Greeting Starter Demo

## Was dieses Beispiel übt

Eine kleine Webanwendung, die den Greeting-Starter wie eine fremde Abhängigkeit benutzt. Der `GreetingService` kommt aus der Auto-Configuration, die Werte aus `application.properties` dieser Demo.

## Wichtige Klassen in Lesereihenfolge

1. `DemoApplication` – Start der Demo
2. `GreetingController` – REST-Fassade über den injizierten `GreetingService`

Die Starter-Klassen liegen im Modul `greeting-spring-boot-starter`.

## Start

In NetBeans `DemoApplication` starten, oder im Modulordner:

```bash
mvn spring-boot:run
```

Die Anwendung lauscht auf Port **8101** (`server.port` in `application.properties`).

## Ausprobieren

1. http://localhost:8101/greet öffnen. Die Antwort begrüßt `World`.
2. http://localhost:8101/greet/Petra öffnen und die formatierte Begrüßung prüfen.
3. `greeting.uppercase=true` setzen, neu starten und beide Pfade erneut aufrufen.
