# 08 Synchronisation

IDE und Maven: [../../../docs/ide-maven.md](../../../docs/ide-maven.md)

## Lernziel

`counter++` aus mehreren Threads verliert Werte, solange der Schritt nicht geschützt ist. Die zweite Stufe überweist zwischen zwei Konten und sperrt immer in derselben Reihenfolge.

## Codebeispiel

`Tag09MiniChallengeApp` zeigt `synchronized`, `AtomicInteger`, Locks, eine Queue, Deadlock, Semaphore und `CountDownLatch`.

Main-Klasse: `de.ibb.tag09.Tag09MiniChallengeApp`

Im Ordner `codebeispiel`: `mvn exec:java`

## Stufe 1

`SichererZaehler.erhoehen` erhöht um 1, `wert` liest den Stand. Beide sind synchronisiert. `parallel(threads, proThread)` lässt so viele Threads je `proThread` Mal erhöhen und liefert den Stand danach.

Abnahme: `parallel(8, 1000)` ist `8000`.

## Stufe 2

`Konto` hat einen Namen und einen Stand. `ueberweisen(von, nach, betrag)` zieht den Betrag ab und zählt ihn dem anderen Konto gut. Beide Konten werden in einer festen Reihenfolge gesperrt (zuerst der alphabetisch kleinere Name), damit zwei gegenläufige Überweisungen sich nicht gegenseitig festhalten. Reicht der Stand nicht, passiert nichts.

Abnahme: Konto A startet bei 1000, Konto B bei 0. Vier Threads überweisen je 250 Mal 1 Euro von A nach B. Danach steht A auf 0 und B auf 1000. Eine einzelne gegenläufige Runde von A=100, B=40 mit 30 von A nach B und 10 von B nach A endet bei A=80 und B=60.

## Abnahme

Die Stände oben. Gerüste: `mvn -q -DskipTests compile`, ohne Testklassen.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2`. Prüfen mit `mvn -q test`.
