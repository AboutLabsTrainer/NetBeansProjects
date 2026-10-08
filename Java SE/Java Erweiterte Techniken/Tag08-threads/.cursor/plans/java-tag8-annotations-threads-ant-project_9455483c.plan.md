---
name: java-tag8-annotations-threads-ant-project
overview: Implement the Tag 8 mini-challenge (Annotations & Multithreading) as a runnable NetBeans Ant project, mirroring the three challenge levels from the article.
todos: []
---

# Plan: Tag 8 Mini-Challenge als NetBeans-Ant-Projekt

## Ziel

Ein lauffähiges NetBeans-Ant-Projekt erstellen, das die Mini-Challenge aus "Tag 8: Annotations & Multithreading" **exakt** nachbaut:

- **Level 1 (Einsteiger)**: `@Author`-Annotation, drei zählende Threads mit `Thread.sleep()`.
- **Level 2 (Fortgeschritten)**: `@Validate`-Annotation + Validator, Thread-Pool mit 10 Aufgaben, Nutzung von `Callable`/`Future`.
- **Level 3 (Profi)**: Einfacher Logging-Annotation-Processor (zur Laufzeit via Reflection), Producer-Consumer mit `ExecutorService`, Performance-Vergleich sequentiell vs. parallel.

## Projekt- und Paketstruktur

- **NetBeans-Ant-Projekt**: `tag08-annotations-threads`
  - NetBeans: "Java with Ant > Java Application" (ohne Main-Klasse automatisch generieren oder wir passen sie an).
- **Source-Paket(e)** (unter `src/`):
  - `de.ibb.tag08.level1`
    - Für `@Author` und einfache Thread-Beispiele.
  - `de.ibb.tag08.level2`
    - Für `@Validate`, Validator, Thread-Pool, `Callable`/`Future`.
  - `de.ibb.tag08.level3`
    - Für Logging-Annotation & Processor, Producer-Consumer, Performance-Vergleich.
  - Optional gemeinsames Utility-Paket `de.ibb.tag08.common` (z.B. Modellklassen, Helfer).
- **Einstiegspunkt**: `de.ibb.tag08.App` mit `main`-Methode, die die drei Level-Demos nacheinander aufruft.
```mermaid
flowchart TD
  app[App.main]
  l1[Level1Demo]
  l2[Level2Demo]
  l3[Level3Demo]
  authorAnn[@Author]
  validateAnn[@Validate]
  logAnn[@LogExecutionTime]

  app --> l1 --> authorAnn
  app --> l2 --> validateAnn
  app --> l3 --> logAnn

  l2 --> executorService
  l2 --> callableFuture
  l3 --> producerConsumer
  l3 --> performanceCompare
```


## Level 1 – Einsteiger

- **`@Author`-Annotation** (`de.ibb.tag08.level1.Author`):
  - Parameter: `String name(); String date();` (ohne Defaults, damit Nutzung explizit ist).
  - `@Retention(RetentionPolicy.RUNTIME)` und `@Target({ElementType.TYPE, ElementType.METHOD})`.
- **Verwendung von `@Author`**:
  - Beispielklasse `SimpleExample` mit annotierter Klasse und/oder Methoden.
  - Optional kleine Ausgabe per Reflection, die alle `@Author`-Annotations im Paket listet.
- **Threads 1–10 zählen**:
  - Klasse `CountingThread` (erweitert `Thread`) oder `CountingTask` (implementiert `Runnable`), die von 1 bis 10 zählt und `Thread.sleep(500)` zwischen den Ausgaben verwendet.
  - In `Level1Demo` drei Threads mit unterschiedlichen Namen starten, Ausgabe klar erkennbar formatieren.

## Level 2 – Fortgeschritten

- **`@Validate`-Annotation** (`de.ibb.tag08.level2.Validate`):
  - Ziel: Felder validieren, ähnlich wie im Artikel-Beispiel `@NotEmpty`/`@Range`, aber alles unter einer Annotation `@Validate`.
  - Attribute z.B.: `boolean notEmpty() default false; int min() default Integer.MIN_VALUE; int max() default Integer.MAX_VALUE; String message() default "Ungültiger Wert";`.
  - `@Retention(RetentionPolicy.RUNTIME)` und `@Target(ElementType.FIELD)`.
- **Domain-Klasse und Validator**:
  - Klasse `User` oder `Person` mit annotierten Feldern (`name`, `age` etc.).
  - Klasse `Validator` mit statischer Methode `validate(Object obj)`, die via Reflection alle Felder mit `@Validate` prüft und eine Liste von Meldungen zurückgibt bzw. auf der Konsole ausgibt.
- **Thread-Pool mit 10 Aufgaben**:
  - In `Level2Demo`: `ExecutorService executor = Executors.newFixedThreadPool(4);`.
  - 10 `Runnable`- oder `Callable`-Aufgaben einreichen, die z.B. simulierte Arbeit tun (z.B. `Thread.sleep(300)` und dann etwas ausgeben).
- **`Callable` und `Future` für Ergebnisse**:
  - Nutzung von `List<Callable<String>>` und `executor.invokeAll(...)` oder einzelne `Future<String> future = executor.submit(callable);`.
  - Ergebnisse einsammeln und ausgeben (mit Timeout-Variante für ein Beispiel).
  - Am Ende immer `executor.shutdown()` (und optional `awaitTermination`).

## Level 3 – Profi

- **Logging-Annotation + Processor (zur Laufzeit)**:
  - Annotation `@LogExecutionTime` in `de.ibb.tag08.level3` mit Attributen `String value() default ""; boolean enabled() default true;` und `@Retention(RUNTIME)`, `@Target(METHOD)`.
  - Klasse `LoggingProcessor` mit statischer Methode `invokeWithLogging(Object target)`, die per Reflection alle Methoden mit `@LogExecutionTime` findet, sie ausführt, Zeit misst und Name/"value" plus Dauer ausgibt (angelehnt an den Artikel).