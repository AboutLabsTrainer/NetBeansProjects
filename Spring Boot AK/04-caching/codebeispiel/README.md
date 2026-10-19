Die Aufgabe dieses Moduls steht in der [README des Elternordners](../README.md).

# Caching mit Caffeine

## Was dieses Beispiel übt

Methoden-Caching über den Spring-Cache-Proxy. Ergebnisse bleiben im Caffeine-Cache, solange der Schlüssel aus den Argumenten gleich bleibt. `CacheEvict` leert alle Regionen.

## Wichtige Klassen in Lesereihenfolge

1. `CachingDemoApplication` – Start
2. `CacheConfig` – schaltet Caching ein und baut den `CacheManager`
3. `CalculatorService` – die gecachten Rechenmethoden
4. `CalculatorController` – HTTP-Fassade und Statistik

## Start

In NetBeans `CachingDemoApplication` starten, oder im Modulordner:

```bash
mvn spring-boot:run
```

In `application.properties` steht kein `server.port`. Die Anwendung lauscht auf Port **8080**.

## Ausprobieren

1. http://localhost:8080/calc/add?a=2&b=3 aufrufen. Die Dauer liegt bei etwa zwei Sekunden, in der Konsole steht, dass gerechnet wird.
2. Dieselbe URL sofort erneut öffnen. Die Dauer fällt auf wenige Millisekunden, eine neue Rechnung erscheint nicht.
3. `POST http://localhost:8080/calc/clear` senden und danach http://localhost:8080/calc/stats öffnen. Nach dem Leeren zeigt der nächste Add-Aufruf wieder einen Miss.
