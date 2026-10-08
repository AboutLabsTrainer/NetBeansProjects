# ScratchPad

Kleine Konsolenbeispiele ohne gemeinsames Thema. Jedes Paket übt einen anderen Sprachbaustein. Es gibt bewusst nur diese eine README.

## Pakete und Klassen mit main

| Paket | Übung | Klasse mit `main` |
| --- | --- | --- |
| `de.simple` | Ausgabe, Referenztypen, Tastatureingabe | `Application`, `ReferenceTypes`, `SmallConversation` |
| `de.array` | Mittelwert eines Arrays | `PrintTheAverage` |
| `de.controlstructures` | `if` in einer Schleife | `HappyWeekend` |
| `de.bitoperations` | Bitweise Operatoren | `BitOperationsExamples` |
| `de.generics` | Typschranken, `Consumer`, Lambdas | `RecyclingBeispiel`, `LambdaGenericVerbindung`, `ConsumerAndPredicate` |
| `de.threading` | `synchronized` und zwei Threads | `Geeks` |
| `de.island.ready` | Wiederholung: Verzweigung, Matrix, Liste, Eingabe | `IfElseTest`, `MatrixArray`, `PrintTheAverage`, `MeineErsteListe`, `SmallConversation` |
| `files.streams` | Dateien lesen und schreiben, Stream-Filter | `ClassicWrite`, `ClassicRead`, `ModernNIOWrite`, `ModernNIORead`, `StreamFilterExample` |

## Start in NetBeans

F6 startet `files.streams.ClassicWrite` (`main`, steht in den Projekteigenschaften). Die Ausgabe geht in die Konsole, kein Server. Andere Klassen: die Datei öffnen, Rechtsklick, Datei ausführen.

## Ausprobieren

1. F6 ausführen. `ClassicWrite` legt `beispiel_alt.txt` an. Danach `ClassicRead` starten und die Zeilen in der Konsole prüfen.
2. `ModernNIOWrite` ausführen, danach `ModernNIORead` und `StreamFilterExample`. Der Filter behält nur Zeilen, die „NIO“ enthalten.
3. Eine Klasse aus einem anderen Paket starten, zum Beispiel `HappyWeekend` oder `MeineErsteListe`.
