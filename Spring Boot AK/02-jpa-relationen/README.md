# JPA-Relationen

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Autor und Buch hängen über `@ManyToOne` und `@OneToMany` zusammen. Die Übung speichert in H2.

## Codebeispiel

Main-Klasse: `de.ibb.boot.ak.relations.PersonManagementApplication`

`server.port`: **8080**

Das Codebeispiel nutzt das Profil `mysql`. Die Übung braucht kein MySQL.

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`LibraryService.link` setzt beide Seiten. Danach gehört das Buch `Notizen` zur Autorin `Ada`.

Port **8304** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8304/api/books/all`

## Stufe 2

`BookRepository.findByAuthor_Name` und `GET /api/books?author=Ada` liefern die Titel dieser Autorin.

Port **8305** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8305/api/books?author=Ada`

## Abnahme

Im Ordner `uebung-stufe-1` und `uebung-stufe-2`:

```bash
mvn -q -DskipTests compile
```

Im Ordner `loesung/stufe-1` und `loesung/stufe-2`:

```bash
mvn -q test
```

## Lösung erst nach eigenem Versuch

Die Lösungen liegen unter `loesung/stufe-1` und `loesung/stufe-2`. Erst öffnen, wenn die eigene Übung kompiliert und das erwartete Verhalten zeigt.
