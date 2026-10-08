# Java Erweiterte Techniken - Tag 8: Annotations & Multithreading

Dieses Projekt demonstriert die Verwendung von **Java-Annotationen** und **Multithreading-Konzepten** in drei aufsteigenden Schwierigkeitsgraden.

## 📋 Projektübersicht

Das Projekt ist in drei Level unterteilt, die verschiedene Aspekte von Annotationen und Multithreading behandeln:

- **Level 1**: Einfache Annotationen und Basis-Threading
- **Level 2**: Validierungs-Annotationen und Thread-Pools
- **Level 3**: Logging-Annotationen, Producer-Consumer-Pattern und Performance-Vergleiche

## 🚀 Ausführung

Die Anwendung kann über die Hauptklasse `App` gestartet werden:

```bash
java de.ibb.tag08.App
```

Die Demo führt automatisch alle drei Level nacheinander aus und zeigt die verschiedenen Konzepte in Aktion.

### Programmablauf

```mermaid
flowchart TD
    Start([App.main startet]) --> Level1[Level 1 Demo]
    Level1 --> L1A[Zeige @Author Annotationen]
    Level1 --> L1B[Starte 3 Counting Threads]
    L1A --> Level2[Level 2 Demo]
    L1B --> Level2
    Level2 --> L2A[Validierung mit @Validate]
    Level2 --> L2B[Thread-Pool Demo]
    Level2 --> L2C[Callable & Future Demo]
    L2A --> Level3[Level 3 Demo]
    L2B --> Level3
    L2C --> Level3
    Level3 --> L3A[Logging & Performance]
    Level3 --> L3B[Producer-Consumer Demo]
    L3A --> Ende([Programm beendet])
    L3B --> Ende
```

## 📁 Projektstruktur

```
src/de/javadeveloper/tag08/
├── App.java                    # Hauptklasse - Einstiegspunkt
├── level1/                     # Level 1: @Author & Threads
│   ├── Author.java            # Custom Annotation für Autoren-Informationen
│   ├── SimpleExample.java     # Beispielklasse mit @Author-Annotationen
│   ├── CountingTask.java      # Runnable-Implementierung für Threads
│   └── Level1Demo.java        # Demo für Level 1
├── level2/                     # Level 2: @Validate & Thread-Pool
│   ├── Validate.java          # Custom Annotation für Feld-Validierung
│   ├── Person.java            # Beispiel-Domainklasse mit @Validate
│   ├── Validator.java         # Reflection-basierter Validator
│   └── Level2Demo.java        # Demo für Level 2
└── level3/                     # Level 3: Logging, Producer-Consumer, Performance
    ├── LogExecutionTime.java  # Custom Annotation für Ausführungszeit-Logging
    ├── LoggingProcessor.java  # Processor für @LogExecutionTime
    ├── PerformanceComparisonDemo.java  # Performance-Vergleich sequentiell vs. parallel
    ├── ProducerConsumerDemo.java       # Producer-Consumer-Pattern Demo
    └── Level3Demo.java         # Demo für Level 3
```

### Abhängigkeitsdiagramm

```mermaid
graph TB
    App[App.java] --> L1[Level1Demo]
    App --> L2[Level2Demo]
    App --> L3[Level3Demo]
    
    L1 --> Author[Author.java]
    L1 --> SimpleEx[SimpleExample.java]
    L1 --> Counting[CountingTask.java]
    SimpleEx -.@Author.-> Author
    
    L2 --> Validate[Validate.java]
    L2 --> Person[Person.java]
    L2 --> Validator[Validator.java]
    Person -.@Validate.-> Validate
    Validator --> Validate
    
    L3 --> LogTime[LogExecutionTime.java]
    L3 --> LogProc[LoggingProcessor.java]
    L3 --> PerfComp[PerformanceComparisonDemo.java]
    L3 --> ProdCons[ProducerConsumerDemo.java]
    PerfComp -.@LogExecutionTime.-> LogTime
    LogProc --> LogTime
```

## 📚 Level 1: @Author & Threads

### Konzepte
- **Custom Annotationen**: Erstellen und Verwenden der `@Author`-Annotation
- **Reflection**: Auslesen von Annotationen zur Laufzeit
- **Basis-Threading**: Erstellen und Starten von Threads mit `Runnable`

### Komponenten

#### `@Author` Annotation
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Author {
    String name();
    String date();
}
```

- Kann auf Klassen und Methoden angewendet werden
- Speichert Autoren-Name und Datum
- Wird zur Laufzeit ausgelesen

#### `CountingTask`
- Implementiert `Runnable`
- Zählt von 1 bis 10 mit Pausen zwischen den Zählvorgängen
- Demonstriert parallele Ausführung mehrerer Threads

### Annotation-Processing (Level 1)

```mermaid
sequenceDiagram
    participant Demo as Level1Demo
    participant Reflect as Reflection API
    participant Class as SimpleExample.class
    participant Method as Method Objects
    participant Author as @Author Annotation

    Demo->>Reflect: getClass()
    Reflect->>Class: SimpleExample.class
    Demo->>Class: isAnnotationPresent(@Author)
    Class-->>Demo: true/false
    Demo->>Class: getAnnotation(@Author)
    Class-->>Demo: Author(name, date)
    
    Demo->>Class: getDeclaredMethods()
    Class-->>Demo: Method[]
    loop Für jede Methode
        Demo->>Method: isAnnotationPresent(@Author)
        Method-->>Demo: true/false
        Demo->>Method: getAnnotation(@Author)
        Method-->>Demo: Author(name, date)
    end
```

### Thread-Ausführung (Level 1)

```mermaid
sequenceDiagram
    participant Main as Main Thread
    participant T1 as Thread-A
    participant T2 as Thread-B
    participant T3 as Thread-C

    Main->>T1: start()
    Main->>T2: start()
    Main->>T3: start()
    
    par Parallel Execution
        T1->>T1: Zähle 1-10 (500ms Pause)
    and
        T2->>T2: Zähle 1-10 (500ms Pause)
    and
        T3->>T3: Zähle 1-10 (500ms Pause)
    end
    
    T1-->>Main: join()
    T2-->>Main: join()
    T3-->>Main: join()
    Main->>Main: Alle Threads beendet
```

### Demo-Ausgabe
- Zeigt alle `@Author`-Annotationen einer Beispielklasse
- Startet drei parallele Zähl-Threads

## 📚 Level 2: @Validate & Thread-Pool

### Konzepte
- **Validierungs-Annotationen**: Feld-Validierung mit `@Validate`
- **Reflection-basierte Validierung**: Automatische Prüfung annotierter Felder
- **ExecutorService**: Thread-Pool-Verwaltung
- **Callable & Future**: Rückgabewerte von Thread-Aufgaben

### Komponenten

#### `@Validate` Annotation
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validate {
    boolean notEmpty() default false;
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
    String message() default "Ungültiger Wert";
}
```

- Wird auf Felder angewendet
- Unterstützt `notEmpty`-Prüfung für Strings
- Unterstützt `min`/`max`-Prüfung für Zahlen

#### `Validator`
- Verwendet Reflection, um alle `@Validate`-annotierten Felder zu finden
- Führt Validierungsprüfungen durch
- Gibt Liste von Fehlermeldungen zurück

#### Validierungsprozess

```mermaid
sequenceDiagram
    participant Demo as Level2Demo
    participant Validator as Validator
    participant Reflect as Reflection API
    participant Person as Person Object
    participant Validate as @Validate Annotation

    Demo->>Validator: validate(Person)
    Validator->>Reflect: getClass()
    Reflect->>Person: Person.class
    Validator->>Reflect: getDeclaredFields()
    Reflect-->>Validator: Field[]
    
    loop Für jedes Feld
        Validator->>Field: isAnnotationPresent(@Validate)
        Field-->>Validator: true/false
        alt Annotation vorhanden
            Validator->>Field: getAnnotation(@Validate)
            Field-->>Validator: Validate(notEmpty, min, max, message)
            Validator->>Field: get(Person)
            Field-->>Validator: Feldwert
            Validator->>Validator: Prüfe Validierungsregeln
            alt Validierung fehlgeschlagen
                Validator->>Validator: Fehlermeldung hinzufügen
            end
        end
    end
    
    Validator-->>Demo: List<String> Fehlermeldungen
```

#### Thread-Pool-Demos
1. **ExecutorService mit Runnable**: 10 Aufgaben werden in einem Pool von 4 Threads ausgeführt
2. **Callable & Future**: 5 Aufgaben mit Rückgabewerten werden parallel ausgeführt und Ergebnisse gesammelt

### Thread-Pool Architektur (Level 2)

```mermaid
graph TB
    subgraph "ExecutorService (4 Threads)"
        T1[Thread-1]
        T2[Thread-2]
        T3[Thread-3]
        T4[Thread-4]
    end
    
    subgraph "Aufgaben Queue"
        Q1[Aufgabe 1]
        Q2[Aufgabe 2]
        Q3[Aufgabe 3]
        Q4[Aufgabe 4]
        Q5[Aufgabe 5]
        Q6[Aufgabe 6]
        Q7[Aufgabe 7]
        Q8[Aufgabe 8]
        Q9[Aufgabe 9]
        Q10[Aufgabe 10]
    end
    
    Q1 --> T1
    Q2 --> T2
    Q3 --> T3
    Q4 --> T4
    Q5 --> T1
    Q6 --> T2
    Q7 --> T3
    Q8 --> T4
    Q9 --> T1
    Q10 --> T2
```

### Callable & Future Pattern

```mermaid
sequenceDiagram
    participant Main as Main Thread
    participant Exec as ExecutorService
    participant Pool as Thread Pool
    participant Future as Future Objects

    Main->>Exec: invokeAll(Callable Tasks)
    Exec->>Pool: Verteile 5 Tasks
    Exec->>Future: Erstelle Future für jede Task
    
    par Parallel Execution
        Pool->>Pool: Task 1 ausführen
        Pool-->>Future: Ergebnis 1
    and
        Pool->>Pool: Task 2 ausführen
        Pool-->>Future: Ergebnis 2
    and
        Pool->>Pool: Task 3 ausführen
        Pool-->>Future: Ergebnis 3
    and
        Pool->>Pool: Task 4 ausführen
        Pool-->>Future: Ergebnis 4
    and
        Pool->>Pool: Task 5 ausführen
        Pool-->>Future: Ergebnis 5
    end
    
    Main->>Future: get() für alle Futures
    Future-->>Main: Ergebnisse
```

### Demo-Ausgabe
- Validierung von `Person`-Objekten mit gültigen und ungültigen Werten
- Parallele Ausführung von 10 Aufgaben in einem Thread-Pool
- Sammlung und Ausgabe von Future-Ergebnissen

## 📚 Level 3: Logging, Producer-Consumer, Performance

### Konzepte
- **Logging-Annotationen**: Automatisches Messen der Ausführungszeit
- **Annotation Processing**: Runtime-Processing von Annotationen
- **Producer-Consumer-Pattern**: Koordination zwischen produzierenden und konsumierenden Threads
- **Performance-Vergleich**: Sequentiell vs. parallel

### Komponenten

#### `@LogExecutionTime` Annotation
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogExecutionTime {
    String value() default "";
    boolean enabled() default true;
}
```

- Wird auf Methoden angewendet
- Misst automatisch die Ausführungszeit
- Kann mit einem Label versehen werden

#### `LoggingProcessor`
- Findet alle Methoden mit `@LogExecutionTime`
- Führt Methoden aus und misst die Dauer
- Gibt die Ausführungszeit aus

#### `PerformanceComparisonDemo`
- **`runSequential()`**: Führt 8 Aufgaben nacheinander aus
- **`runParallel()`**: Führt 8 Aufgaben parallel in einem Thread-Pool aus
- Beide Methoden sind mit `@LogExecutionTime` annotiert
- Zeigt den Performance-Unterschied zwischen sequentieller und paralleler Ausführung

#### Logging-Processor Pipeline

```mermaid
sequenceDiagram
    participant Demo as Level3Demo
    participant Processor as LoggingProcessor
    participant Reflect as Reflection API
    participant Method as Method Objects
    participant LogTime as @LogExecutionTime
    participant Target as PerformanceComparisonDemo

    Demo->>Processor: invokeWithLogging(Target)
    Processor->>Reflect: getClass()
    Reflect->>Target: PerformanceComparisonDemo.class
    Processor->>Reflect: getDeclaredMethods()
    Reflect-->>Processor: Method[]
    
    loop Für jede Methode
        Processor->>Method: isAnnotationPresent(@LogExecutionTime)
        Method-->>Processor: true/false
        alt Annotation vorhanden
            Processor->>Method: getAnnotation(@LogExecutionTime)
            Method-->>Processor: LogExecutionTime(value, enabled)
            alt enabled == true
                Processor->>Processor: Startzeit messen
                Processor->>Method: invoke(Target)
                Method->>Target: Methode ausführen
                Target-->>Method: Rückgabe
                Method-->>Processor: Rückgabe
                Processor->>Processor: Endzeit messen
                Processor->>Processor: Dauer berechnen & loggen
            end
        end
    end
```

#### Performance-Vergleich: Sequentiell vs. Parallel

```mermaid
gantt
    title Performance-Vergleich: 8 Aufgaben
    dateFormat X
    axisFormat %s
    
    section Sequentiell
    Aufgabe 1 (100ms)    :0, 100
    Aufgabe 2 (100ms)    :100, 100
    Aufgabe 3 (100ms)    :200, 100
    Aufgabe 4 (100ms)    :300, 100
    Aufgabe 5 (100ms)    :400, 100
    Aufgabe 6 (100ms)    :500, 100
    Aufgabe 7 (100ms)    :600, 100
    Aufgabe 8 (100ms)    :700, 100
    
    section Parallel (4 Threads)
    Aufgabe 1 (100ms)    :0, 100
    Aufgabe 2 (100ms)    :0, 100
    Aufgabe 3 (100ms)    :0, 100
    Aufgabe 4 (100ms)    :0, 100
    Aufgabe 5 (100ms)    :100, 100
    Aufgabe 6 (100ms)    :100, 100
    Aufgabe 7 (100ms)    :100, 100
    Aufgabe 8 (100ms)    :100, 100
```

#### `ProducerConsumerDemo`
- Verwendet `BlockingQueue` für Thread-sichere Kommunikation
- **Producer**: Erstellt 10 Items und legt sie in die Queue
- **Consumer**: Zwei Consumer-Threads verarbeiten Items aus der Queue
- Demonstriert koordinierte Arbeitsteilung zwischen Threads

### Producer-Consumer Pattern

```mermaid
graph LR
    subgraph "Producer Thread"
        P[Producer]
        P -->|put Item| Q[BlockingQueue<br/>Kapazität: 5]
    end
    
    subgraph "Consumer Threads"
        C1[Consumer-1]
        C2[Consumer-2]
    end
    
    Q -->|take Item| C1
    Q -->|take Item| C2
    
    style Q fill:#e1f5ff
    style P fill:#ffe1f5
    style C1 fill:#f5ffe1
    style C2 fill:#f5ffe1
```

### Producer-Consumer Sequenzdiagramm

```mermaid
sequenceDiagram
    participant P as Producer Thread
    participant Q as BlockingQueue
    participant C1 as Consumer-1
    participant C2 as Consumer-2

    loop 10 Items
        P->>P: Erstelle Item
        P->>Q: put(Item)
        Note over Q: Queue blockiert wenn voll
        Q-->>P: Item hinzugefügt
    end
    
    P->>Q: put("END")
    
    par Consumer Processing
        loop Items verarbeiten
            C1->>Q: take()
            Q-->>C1: Item
            alt Item == "END"
                C1->>Q: put("END")
                C1->>C1: Beende Loop
            else
                C1->>C1: Verarbeite Item (150ms)
            end
        end
    and
        loop Items verarbeiten
            C2->>Q: take()
            Q-->>C2: Item
            alt Item == "END"
                C2->>Q: put("END")
                C2->>C2: Beende Loop
            else
                C2->>C2: Verarbeite Item (150ms)
            end
        end
    end
```

### Demo-Ausgabe
- Ausführungszeiten für sequentielle und parallele Verarbeitung
- Producer-Consumer-Interaktion mit Queue-basierter Kommunikation

## 🔑 Wichtige Java-Konzepte

### Annotationen

```mermaid
mindmap
  root((Annotationen))
    Retention
      SOURCE
      CLASS
      RUNTIME
    Target
      TYPE
      METHOD
      FIELD
      PARAMETER
    Reflection API
      getAnnotation()
      isAnnotationPresent()
      getDeclaredMethods()
      getDeclaredFields()
```

- **@Retention**: Bestimmt, wann Annotationen verfügbar sind (`RUNTIME` für Reflection)
- **@Target**: Bestimmt, wo Annotationen angewendet werden können
- **Reflection**: `Class.getAnnotation()`, `Method.getAnnotation()`, `Field.getAnnotation()`

### Multithreading

```mermaid
mindmap
  root((Multithreading))
    Thread Erstellung
      Thread Klasse
      Runnable Interface
      Callable Interface
    Thread Management
      ExecutorService
      Thread Pool
      Future
    Synchronisation
      BlockingQueue
      join()
      synchronized
```

- **Thread**: Basis-Klasse für Thread-Erstellung
- **Runnable**: Interface für ausführbare Aufgaben
- **Callable**: Interface für Aufgaben mit Rückgabewert
- **ExecutorService**: Verwaltung von Thread-Pools
- **Future**: Repräsentiert das Ergebnis einer asynchronen Berechnung
- **BlockingQueue**: Thread-sichere Queue für Producer-Consumer-Pattern

## 🛠️ Technische Details

- **Java Version**: Kompatibel mit Java SE 8+
- **Build-System**: Apache Ant (NetBeans-Projekt)
- **Package-Struktur**: `de.ibb.tag08`

## 📝 Lernziele

Nach dem Durcharbeiten dieses Projekts sollten Sie verstehen:

1. ✅ Wie man eigene Annotationen erstellt und verwendet
2. ✅ Wie man Annotationen zur Laufzeit mit Reflection ausliest
3. ✅ Grundlagen des Multithreading in Java
4. ✅ Verwendung von `ExecutorService` und Thread-Pools
5. ✅ Arbeiten mit `Callable` und `Future`
6. ✅ Implementierung des Producer-Consumer-Patterns
7. ✅ Performance-Vorteile paralleler Ausführung

## 📖 Weitere Informationen

Dieses Projekt ist Teil einer Java-Schulungsreihe und demonstriert praktische Anwendungen erweiterter Java-Techniken. Die drei Level bauen aufeinander auf und zeigen zunehmend komplexere Konzepte.
