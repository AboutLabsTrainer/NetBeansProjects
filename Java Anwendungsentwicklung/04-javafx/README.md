# 04 – JavaFX

JavaFX aus `Tag05-javafx`: Hallo-Fenster, Property Binding, CSS und FXML. JavaFX 21 bleibt Abhängigkeit.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie formatieren den Sliderwert wie die Textbindung und halten ihn im Bereich des Sliders, ohne eine Stage zu öffnen.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.fx.HalloJavaFX`.

```bash
mvn -q -DskipTests compile
```

Mit Anzeige: `mvn javafx:run`. Weitere Mains im selben Paket: `BindingDemoApp`, `CssDemoApp`, `FxmlDemoApp`. `BindingDemoApp` bindet das Label an `Wert: %.0f`. Der Slider läuft von 0 bis 100. JavaFX Controls und FXML 21 stehen im POM. Kompilieren braucht keine Anzeige.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `SliderText`.

`format` liefert denselben Text wie `asString("Wert: %.0f")`.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `SliderBereich`.

Werte unter 0 werden 0, Werte über 100 werden 100.

## Abnahme

- `format(50.6)` ist `Wert: 51`.
- `begrenze(140)` ist 100.
- Codebeispiel kompiliert ohne Anzeige. Lösungen: `mvn -q test` ohne JavaFX-Fenster.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
