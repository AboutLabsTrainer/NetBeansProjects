# Tag 5 – JavaFX

## Was dieses Beispiel übt

JavaFX statt Swing: eine Scene mit Controls, Property Binding, CSS und eine Oberfläche aus FXML mit Controller.

## Klassen

In dieser Reihenfolge lesen:

1. `HalloJavaFX` – Stage, Scene, Label, Button
2. `BindingDemoApp` – Slider und Label über ein Binding
3. `CssDemoApp` – Style-Klassen und `styles.css`
4. `Controller` – die Methode, die der Button in der FXML aufruft
5. `FxmlDemoApp` – lädt `view.fxml`

## Start in NetBeans

F6 startet `de.ibb.fx.HalloJavaFX` (`main`). Es öffnet sich ein Fenster, kein Server. Die anderen Klassen haben ein eigenes `main`: Rechtsklick auf die Klasse, Datei ausführen.

## Ausprobieren

1. In „Hallo JavaFX“ auf „Klick mich!“ drücken. Der Label-Text wechselt.
2. `BindingDemoApp` starten und den Slider ziehen. Das Label folgt ohne eigenen Listener.
3. `CssDemoApp` und danach `FxmlDemoApp` starten. In der FXML-Demo ändert der Button den Text über `Controller`.
