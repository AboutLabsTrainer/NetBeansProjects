# 05 – Contact Editor

Eigenes Modul für den früheren Ordner `ContactEditor`: ein Swing-Formular mit den Bereichen Name und E-Mail.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie prüfen einen Kontakt aus Name und E-Mail und normalisieren die Adresse, ohne das Formular zu öffnen.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Das Fenster startet `de.ibb.contacteditor.ContactEditorUI`.

```bash
mvn -q -DskipTests compile
```

`ContactEditor.main` ist im Beispiel leer und bleibt so. Die beiden Flächen heißen Name und E-Mail. Swing kommt aus dem JDK.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `Kontakt`.

`istVollstaendig` ist wahr, wenn der Name nicht leer ist und die E-Mail ein `@` enthält.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `EmailNorm`.

`normalisiere` schneidet Leerzeichen ab und schreibt klein. `null` bleibt `null`.

## Abnahme

- `Ada` und `ada@ibb.de` sind vollständig, ein Name aus Leerzeichen nicht.
- `  Ada@IBB.de ` wird `ada@ibb.de`.
- Codebeispiel kompiliert ohne Anzeige. Lösungen: `mvn -q test`.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
