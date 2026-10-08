# Contact Editor

## Was dieses Beispiel übt

Der NetBeans-GUI-Builder: ein `JFrame` und ein `JPanel` als Formular, erzeugt aus den `.form`-Dateien. Die Hand-Logik ist absichtlich noch leer.

## Klassen

In dieser Reihenfolge lesen:

1. `ContactEditorUI` – das Fenster mit den beiden Panels und dem `main`, das es anzeigt
2. `JFrameFormTest` – ein zweites, leeres Frame aus dem Formular-Editor
3. `JPanelTest` – ein Panel aus dem Formular-Editor
4. `ContactEditor` – die Projekt-Startklasse; `main` enthält noch keine Logik

## Start in NetBeans

F6 startet `contacteditor.ContactEditor`. Das Fenster bleibt zu, weil `main` leer ist. Für die Oberfläche: `ContactEditorUI` öffnen, Rechtsklick, Datei ausführen.

## Ausprobieren

1. `ContactEditorUI` ausführen. Es erscheint ein Fenster mit den Bereichen „Name“ und einem zweiten Panel.
2. In der Ansicht „Design“ die `.form`-Datei ansehen und mit dem Quelltext von `initComponents` vergleichen.
3. F6 ausführen und in `ContactEditor.main` nachsehen, warum kein Fenster aufgeht.
