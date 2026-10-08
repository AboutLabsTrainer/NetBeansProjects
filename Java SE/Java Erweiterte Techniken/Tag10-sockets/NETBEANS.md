# NetBeans Quick-Reference

Schnellreferenz für die Demonstration des Projekts in NetBeans.

## Ports

- **Echo-Server** (EchoServerLevel1, MultiClientEchoServer): Port **9999**
- **Chat-Server**: Port **9998**
- **Payara** läuft auf Port 8080 (kein Konflikt)

## Schnellstart

### Echo Server/Client

1. **Server starten:**
   - Rechtsklick auf `EchoServerLevel1.java` → **Run File** (F6)
   - Server läuft auf Port 9999

2. **Client starten:**
   - Rechtsklick auf `EchoClientLevel1.java` → **Run File** (F6)
   - Verbindet sich automatisch mit localhost:9999
   - Nachrichten eingeben, "bye" zum Beenden

### Chat Server/Client

1. **Server starten:**
   - Rechtsklick auf `ChatServer.java` → **Run File** (F6)
   - Server läuft auf Port 9998

2. **Mehrere Clients starten:**
   - Rechtsklick auf `ChatClient.java` → **Run File** (F6)
   - Wiederholen für weitere Clients (jeder in separatem Output-Fenster)
   - Nachrichten testen, "/quit" zum Verlassen

### HTTP-Beispiele

- `SimpleHttpGetExample.java` → **Run File** (F6)
- `HttpPostJsonExample.java` → **Run File** (F6)
- `ParallelApiCallsExample.java` → **Run File** (F6)

## Tastenkürzel

| Aktion | Tastenkürzel |
|--------|--------------|
| Run File | F6 |
| Debug File | F5 |
| Build Project | F11 |
| Clean & Build | Shift+F11 |
| Projects-Fenster | Strg+1 |
| Output-Fenster | Strg+4 |
| Navigator | Strg+7 |

## Output-Fenster

- Jedes "Run File" erstellt ein eigenes Output-Fenster
- **Window → Output → Output** (Strg+4) zeigt alle Fenster
- Fenster können nebeneinander angeordnet werden

## Debugging

1. **Breakpoint setzen:** Linksklick in Zeilennummernbereich (blaue Markierung)
2. **Debug File** (F5) starten
3. **Debug-Toolbar:**
   - **Continue** (F5) - Weiter bis nächster Breakpoint
   - **Step Over** (F8) - Nächste Zeile (ohne in Methoden einzusteigen)
   - **Step Into** (F7) - Nächste Zeile (steigt in Methoden ein)
   - **Step Out** (Ctrl+F7) - Aus aktueller Methode herausspringen
   - **Pause** - Programm pausieren
   - **Finish** - Programm beenden

**Hinweis:** Debug-Targets (`debug`, `debug-single`) sind in `build.xml` konfiguriert und verwenden JDWP auf Port 5005.

## Ant-Targets

**Rechtsklick auf Projekt → Run Target:**

### Run-Targets
- `run` - Führt Standard-Klasse aus (EchoServerLevel1) oder Klasse aus `run.class` Property
- `run-echo-server` - EchoServerLevel1 (Port 9999)
- `run-echo-client` - EchoClientLevel1
- `run-multi-server` - MultiClientEchoServer (Port 9999)
- `run-chat-server` - ChatServer (Port 9998)
- `run-chat-client` - ChatClient
- `run-http-get` - SimpleHttpGetExample
- `run-http-post` - HttpPostJsonExample
- `run-parallel-api` - ParallelApiCallsExample

### Debug-Targets
- `debug` - Debuggt Klasse aus `debug.class` Property
- `debug-single` - Debuggt einzelne Datei (verwendet `run.class` oder `debug.class`)

### Build-Targets
- `compile` - Kompiliert alle Klassen
- `compile-single` - Kompiliert einzelne Datei (verwendet `javac.includes`)
- `clean` - Löscht Build-Verzeichnisse
- `jar` - Erstellt JAR-Datei

### NetBeans-spezifische Targets
- `run-single` - Führt einzelne Datei aus (wird automatisch von NetBeans verwendet)
- `debug-single` - Debuggt einzelne Datei (wird automatisch von NetBeans verwendet)

**Hinweis:** Die `-single` Targets werden automatisch von NetBeans aufgerufen, wenn du "Run File" oder "Debug File" verwendest.
