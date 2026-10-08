# Tag 9: Multithreading – Synchronisation

## 📋 Projektübersicht

Dieses Projekt implementiert die **Mini-Challenge** aus **Tag 9: Multithreading – Synchronisation** des Kurses "Java Erweiterte Techniken". Es demonstriert verschiedene Synchronisationsmechanismen in Java, von einfachen `synchronized`-Blöcken bis hin zu fortgeschrittenen `java.util.concurrent`-Klassen.

Das Projekt ist als **Ant-basiertes NetBeans-Projekt** aufgebaut und enthält alle drei Challenge-Level (Einsteiger, Fortgeschritten, Profi) mit vollständigen, lauffähigen Beispielen.

## 🎯 Lernziele

- ✅ Race Conditions verstehen und vermeiden
- ✅ `synchronized`-Blöcke und Methoden anwenden
- ✅ `volatile` für Sichtbarkeit verstehen
- ✅ `java.util.concurrent.locks` (ReentrantLock, ReadWriteLock) nutzen
- ✅ Atomic-Klassen für lockfreie Operationen verwenden
- ✅ Thread-sichere Collections einsetzen
- ✅ Deadlocks erkennen und vermeiden
- ✅ Semaphore und CountDownLatch praktisch anwenden

## 📁 Projektstruktur

```
09-multithreading-synchronisation/
├── build.xml                    # Ant-Build-Skript
├── nbproject/                   # NetBeans-Projektkonfiguration
│   ├── project.xml
│   ├── project.properties
│   └── build-impl.xml
├── src/
│   └── de/javadeveloper/tag09/
│       ├── Tag09MiniChallengeApp.java    # Main-Klasse
│       │
│       ├── Level1SynchronizedCounter.java      # Level 1: synchronized
│       ├── Level1AtomicCounter.java            # Level 1: AtomicInteger
│       ├── Level1ThreadSafeValue.java          # Level 1: Thread-safe Getter/Setter
│       │
│       ├── Level2LockStack.java                # Level 2: Stack mit ReentrantLock
│       ├── Level2ReadWriteCache.java            # Level 2: Cache mit ReadWriteLock
│       ├── Level2BlockingQueueDemo.java        # Level 2: Producer-Consumer
│       │
│       ├── Level3DeadlockDemo.java             # Level 3: Deadlock + Lösung
│       ├── Level3ConnectionPool.java           # Level 3: Semaphore
│       └── Level3InitWithCountDownLatch.java   # Level 3: CountDownLatch
└── README.md
```

## 🚀 Ausführung

### In NetBeans

1. **Projekt öffnen**: `File → Open Project...` → Ordner `09-multithreading-synchronisation` wählen
2. **Projekt ausführen**: Rechtsklick auf Projekt → `Run Project` (oder `Shift+F6`)
3. Die Konsole zeigt nacheinander alle Demos der drei Challenge-Level

### Mit Ant (Kommandozeile)

```bash
# Kompilieren und ausführen
ant run

# Oder nur kompilieren
ant compile

# Clean Build + JAR erstellen
ant clean jar
```

## 📚 Challenge-Levels im Detail

### 🟢 Level 1: Einsteiger

#### 1. Synchronized Counter

Demonstriert Thread-Sicherheit mit dem `synchronized`-Keyword.

**Klasse**: `Level1SynchronizedCounter`

```mermaid
sequenceDiagram
    participant T1 as Thread 1
    participant T2 as Thread 2
    participant Counter as SynchronizedCounter
    
    T1->>Counter: increment() [LOCK]
    Note over Counter: synchronized blockiert
    Counter-->>T1: counter++
    Counter-->>T1: [UNLOCK]
    
    T2->>Counter: increment() [LOCK]
    Note over Counter: Thread 2 wartet
    Counter-->>T2: counter++
    Counter-->>T2: [UNLOCK]
```

**Kernkonzept**: Nur ein Thread kann gleichzeitig eine `synchronized`-Methode ausführen. Andere Threads warten automatisch.

#### 2. AtomicInteger Counter

Lockfreie Thread-Sicherheit mit Atomic-Operationen.

**Klasse**: `Level1AtomicCounter`

```mermaid
graph LR
    A[Thread 1] -->|incrementAndGet| C[AtomicInteger]
    B[Thread 2] -->|incrementAndGet| C
    C -->|CAS Operation| D[Thread-safe]
    
    style C fill:#e1f5ff
    style D fill:#c8e6c9
```

**Vorteil**: Keine Blockierung, sehr performant für einfache Zähler-Operationen.

#### 3. Thread-sichere Getter/Setter

Synchronisierte Zugriffe auf gemeinsame Daten.

**Klasse**: `Level1ThreadSafeValue`

```mermaid
sequenceDiagram
    participant W as Writer Thread
    participant V as ThreadSafeValue
    participant R as Reader Thread
    
    W->>V: setValue(42) [LOCK]
    V-->>W: [UNLOCK]
    
    R->>V: getValue() [LOCK]
    V-->>R: 42
    V-->>R: [UNLOCK]
```

---

### 🟡 Level 2: Fortgeschritten

#### 1. Thread-sicherer Stack mit ReentrantLock

Feingranulare Kontrolle über Locking mit `ReentrantLock`.

**Klasse**: `Level2LockStack<T>`

```mermaid
graph TD
    A[push/pop Operation] --> B{Lock verfügbar?}
    B -->|Ja| C[lock.lock]
    B -->|Nein| D[Thread wartet]
    D --> B
    C --> E[Kritischer Bereich]
    E --> F[Stack.push/pop]
    F --> G[lock.unlock in finally]
    
    style E fill:#fff3e0
    style G fill:#c8e6c9
```

**Vorteile gegenüber `synchronized`**:
- `tryLock()` mit Timeout möglich
- `lockInterruptibly()` für unterbrechbare Locks
- Fairness-Option

#### 2. Cache mit ReadWriteLock

Paralleles Lesen, exklusives Schreiben.

**Klasse**: `Level2ReadWriteCache`

```mermaid
graph LR
    subgraph "ReadWriteLock"
        RL[ReadLock]
        WL[WriteLock]
    end
    
    R1[Reader 1] --> RL
    R2[Reader 2] --> RL
    R3[Reader 3] --> RL
    W1[Writer] --> WL
    
    RL -->|Parallel OK| Cache[Cache]
    WL -->|Exklusiv| Cache
    
    style RL fill:#c8e6c9
    style WL fill:#ffcdd2
    style Cache fill:#e1f5ff
```

**Anwendungsfall**: Viele Lesezugriffe, wenige Schreibzugriffe (z.B. Konfigurations-Cache).

#### 3. Producer-Consumer mit BlockingQueue

Thread-sichere Kommunikation zwischen Produzenten und Konsumenten.

**Klasse**: `Level2BlockingQueueDemo`

```mermaid
graph TD
    subgraph "Producer Threads"
        P1[Producer 1]
        P2[Producer 2]
    end
    
    subgraph "BlockingQueue"
        Q[Queue]
    end
    
    subgraph "Consumer Threads"
        C1[Consumer 1]
        C2[Consumer 2]
    end
    
    P1 -->|put/take| Q
    P2 -->|put/take| Q
    Q -->|take/put| C1
    Q -->|take/put| C2
    
    style Q fill:#e1f5ff
    style P1 fill:#c8e6c9
    style P2 fill:#c8e6c9
    style C1 fill:#fff3e0
    style C2 fill:#fff3e0
```

**Besonderheit**: `put()` blockiert, wenn Queue voll; `take()` blockiert, wenn Queue leer.

---

### 🔴 Level 3: Profi

#### 1. Deadlock-Demo und Lösung

Demonstriert, wie Deadlocks entstehen und wie man sie vermeidet.

**Klasse**: `Level3DeadlockDemo`

```mermaid
graph TD
    subgraph "Deadlock-Szenario"
        T1[Thread 1] -->|Lock A| LA[Lock A]
        T2[Thread 2] -->|Lock B| LB[Lock B]
        T1 -.->|wartet auf B| LB
        T2 -.->|wartet auf A| LA
        LA -.->|DEADLOCK| DL[💀 Deadlock]
        LB -.->|DEADLOCK| DL
    end
    
    subgraph "Lösung: Gleiche Lock-Reihenfolge"
        T3[Thread 3] -->|Lock A| LA2[Lock A]
        T4[Thread 4] -->|Lock A| LA2
        T3 -->|Lock B| LB2[Lock B]
        T4 -.->|wartet| LA2
        LA2 -->|OK| Success[✅ Erfolg]
    end
    
    style DL fill:#ffcdd2
    style Success fill:#c8e6c9
```

**Lösungsansätze**:
1. **Gleiche Lock-Reihenfolge**: Immer zuerst Lock A, dann Lock B
2. **tryLock mit Timeout**: Verhindert endloses Warten

#### 2. ConnectionPool mit Semaphore

Begrenzt gleichzeitige Zugriffe auf eine Ressource.

**Klasse**: `Level3ConnectionPool`

```mermaid
graph TD
    subgraph "10 Threads wollen Connection"
        T1[Thread 1]
        T2[Thread 2]
        T3[Thread 3]
        T4[Thread 4]
        T5[Thread 5]
        T6[Thread 6]
        T7[Thread 7]
        T8[Thread 8]
        T9[Thread 9]
        T10[Thread 10]
    end
    
    subgraph "Semaphore (max 3 Permits)"
        S[Semaphore: 3]
    end
    
    subgraph "Connection Pool"
        CP[Pool]
    end
    
    T1 -->|acquire| S
    T2 -->|acquire| S
    T3 -->|acquire| S
    T4 -.->|wartet| S
    T5 -.->|wartet| S
    T6 -.->|wartet| S
    
    S -->|3 aktiv| CP
    
    CP -->|release| S
    
    style S fill:#e1f5ff
    style CP fill:#c8e6c9
```

**Anwendungsfall**: Datenbankverbindungen, Netzwerkverbindungen, Datei-Handles begrenzen.

#### 3. Parallele Initialisierung mit CountDownLatch

Wartet auf mehrere Threads, bevor der Hauptthread fortfährt.

**Klasse**: `Level3InitWithCountDownLatch`

```mermaid
sequenceDiagram
    participant Main as Main Thread
    participant Latch as CountDownLatch(5)
    participant W1 as Worker 1
    participant W2 as Worker 2
    participant W3 as Worker 3
    participant W4 as Worker 4
    participant W5 as Worker 5
    
    Main->>Latch: await() [blockiert]
    
    par Parallel
        W1->>Latch: countDown() [5→4]
        W2->>Latch: countDown() [4→3]
        W3->>Latch: countDown() [3→2]
        W4->>Latch: countDown() [2→1]
        W5->>Latch: countDown() [1→0]
    end
    
    Latch-->>Main: [0 erreicht, Main wird freigegeben]
    Main->>Main: "Alle Worker fertig!"
```

**Anwendungsfall**: Parallele Initialisierung mehrerer Komponenten, bevor die Anwendung startet.

---

## 🔍 Wichtige Konzepte im Überblick

### Synchronisation-Mechanismen

| Mechanismus | Verwendung | Thread-Safety | Performance |
|------------|------------|---------------|-------------|
| `synchronized` | Einfache Fälle | ✅ | Mittel |
| `ReentrantLock` | Komplexe Anforderungen | ✅ | Mittel |
| `AtomicInteger/Long` | Zähler, Flags | ✅ | Sehr hoch |
| `ReadWriteLock` | Viele Leser, wenige Schreiber | ✅ | Hoch (Lesen) |
| `BlockingQueue` | Producer-Consumer | ✅ | Hoch |
| `Semaphore` | Ressourcen-Begrenzung | ✅ | Mittel |
| `CountDownLatch` | Warten auf Threads | ✅ | Hoch |

### Thread-sichere Collections

| Standard | Thread-safe Alternative | Wann verwenden? |
|----------|------------------------|-----------------|
| `ArrayList` | `CopyOnWriteArrayList` | Viele Leser, wenige Schreiber |
| `HashMap` | `ConcurrentHashMap` | Map mit vielen Zugriffen |
| `HashSet` | `ConcurrentHashMap.newKeySet()` | Thread-sicheres Set |
| `Queue` | `BlockingQueue` | Producer-Consumer-Pattern |

---

## ⚠️ Häufige Fallen

### 1. Race Condition bei `counter++`

```java
// ❌ FALSCH: Nicht thread-safe
private int counter = 0;
public void increment() {
    counter++; // 3 Operationen: Lesen, Erhöhen, Schreiben
}

// ✅ RICHTIG: synchronized
public synchronized void increment() {
    counter++;
}

// ✅ BESSER: AtomicInteger
private AtomicInteger counter = new AtomicInteger(0);
public void increment() {
    counter.incrementAndGet();
}
```

### 2. Deadlock durch unterschiedliche Lock-Reihenfolge

```java
// ❌ FALSCH: Deadlock-Gefahr
Thread 1: lockA → lockB
Thread 2: lockB → lockA

// ✅ RICHTIG: Gleiche Reihenfolge
Thread 1: lockA → lockB
Thread 2: lockA → lockB
```

### 3. `volatile` macht nicht atomar

```java
// ❌ FALSCH: volatile garantiert nur Sichtbarkeit, nicht Atomarität
private volatile int count = 0;
count++; // IMMER NOCH NICHT THREAD-SAFE!

// ✅ RICHTIG: AtomicInteger
private AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet(); // Thread-safe
```

### 4. Spurious Wakeups bei `wait()`

```java
// ❌ FALSCH: if-Schleife
if (queue.isEmpty()) {
    wait(); // Kann ohne notify() aufwachen!
}

// ✅ RICHTIG: while-Schleife
while (queue.isEmpty()) {
    wait(); // Prüft erneut nach Aufwachen
}
```

---

## 🛠️ Technische Details

### Java-Version
- **Minimum**: Java 17
- **Empfohlen**: Java 17 oder höher

### Build-System
- **Ant** (Apache Ant)
- NetBeans-integriert

### Projekt-Konfiguration
- **Main-Klasse**: `de.ibb.tag09.Tag09MiniChallengeApp`
- **Source-Verzeichnis**: `src/`
- **Build-Verzeichnis**: `build/classes/`
- **Dist-Verzeichnis**: `dist/`

---

## 📖 Weiterführende Ressourcen

- [Oracle: Java Concurrency Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [Baeldung: java.util.concurrent](https://www.baeldung.com/java-util-concurrent)
- [Java Concurrency in Practice (Buch)](https://jcip.net/)

---

## 📝 Lizenz

Dieses Projekt ist Teil des Kurses "Java Erweiterte Techniken" und dient ausschließlich zu Lernzwecken.

---

## 👤 Autor

Erstellt im Rahmen von **Tag 9: Multithreading – Synchronisation** des Java-Erweiterte-Techniken-Kurses.

**Quelle**: [java-developer.online - Tag 9](https://www.java-developer.online/tag-9-multithreading-synchronisation/)
