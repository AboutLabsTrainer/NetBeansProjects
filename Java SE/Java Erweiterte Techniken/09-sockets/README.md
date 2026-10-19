# 09 Sockets

IDE und Maven: [../../../docs/ide-maven.md](../../../docs/ide-maven.md)

## Lernziel

Der Echo-Server schickt jede Zeile als `Echo: ...` zurück und beendet die Runde bei `bye`. Der Chat merkt sich Namen und entscheidet, wer eine Zeile bekommt. Diese Regeln lassen sich ohne offenen Port prüfen.

## Codebeispiel

Die Klassen unter `de.ibb.netzwerk.tag10` sind der laufende Server, der Client und die HTTP-Beispiele. `mvn exec:java` startet `EchoServerLevel1` und wartet auf Port 9999, bis der Prozess beendet wird. HTTP-Beispiele rufen `jsonplaceholder.typicode.com` auf. Beides braucht ein Netz und ist kein automatischer Test.

Main-Klasse: `de.ibb.netzwerk.tag10.EchoServerLevel1`

Weitere Klassen im selben Paket: `EchoClientLevel1`, `MultiClientEchoServer`, `ChatServer` (Port 9998), `ChatClient`, `SimpleHttpGetExample`, `HttpPostJsonExample`, `ParallelApiCallsExample`.

Im Ordner `codebeispiel`: `mvn -q -DskipTests compile` prüft den Quelltext. Start einer anderen Klasse: `mvn exec:java -Dexec.mainClass=de.ibb.netzwerk.tag10.EchoClientLevel1`

## Stufe 1

`EchoProtokoll` ist der reine Kern, ohne `ServerSocket`.

- `antwort` liefert `Echo: ` plus die Zeile.
- `beenden` ist wahr für `bye` in beliebiger Großschreibung, ohne die Zeile zu trimmen. `BYE` ist wahr, `hallo` ist falsch, `null` ist falsch.
- `idAusJson` sucht das erste `"id"` gefolgt von einem Integer, dasselbe Muster wie `HttpPostJsonExample`. Fehlt es, ist das `Optional` leer.

Abnahme: `antwort("Hallo")` ist `Echo: Hallo`. `idAusJson` von `{"id": 42, "title": "x"}` ist `42`. `{}` ist leer.

## Stufe 2

`ChatRaum` führt Namen in der Beitrittsreihenfolge. `beitreten` lehnt einen Namen ab, der schon drin ist. `beitrittsEmpfaenger` sind alle außer dem genannten Namen (Beitrittsnachricht geht nicht an den Neuankömmling zurück). `nachrichtenEmpfaenger` sind alle, auch der Absender. `zeile` baut `[absender] text`. `istQuit` erkennt `/quit` nach `trim`, Großschreibung egal.

Abnahme: nacheinander A, B und C. Empfänger des Beitritts von C sind `[A, B]`. Nachrichtenempfänger sind `[A, B, C]`. `zeile("pool-1-thread-1", "Hallo")` ist `[pool-1-thread-1] Hallo`. ` /quit ` ist ein Quit. Nach `verlassen("B")` sind die Nachrichtenempfänger `[A, C]`. Ein zweites `beitreten("A")` ist `false`.

## Abnahme

Die Zeichenketten oben, ohne Port und ohne HTTP. Gerüste: `mvn -q -DskipTests compile`, ohne Testklassen.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2`. Prüfen mit `mvn -q test`.
