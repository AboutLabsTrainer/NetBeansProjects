package de.ibb.collections.todo.ui;

import de.ibb.collections.todo.logic.TodoListService;
import de.ibb.collections.todo.model.Priority;
import de.ibb.collections.todo.model.TodoItem;

import java.util.List;
import java.util.Scanner;

/**
 * Kleine Konsolen-Anwendung, die die drei Level der Mini-Challenge demonstriert.
 *
 * Menü:
 * 1 - Level 1 (Basis ToDo-Liste)
 * 2 - Level 2 (Prioritäten & Undo)
 * 3 - Level 3 (SimpleList-Implementierung)
 * 0 - Beenden
 */
public class TodoAppConsole {

    public static void main(String[] args) {
        new TodoAppConsole().run();
    }

    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMainMenu();
                String input = scanner.nextLine();
                switch (input) {
                    case "1":
                        runLevel1Demo();
                        break;
                    case "2":
                        runLevel2Demo();
                        break;
                    case "3":
                        runLevel3Demo();
                        break;
                    case "0":
                        System.out.println("Beende Programm. Danke fürs Ausprobieren der Mini-Challenge!");
                        return;
                    default:
                        System.out.println("Ungültige Eingabe. Bitte 0, 1, 2 oder 3 wählen.");
                }
                System.out.println();
                System.out.println("Drücke Enter, um zum Hauptmenü zurückzukehren...");
                scanner.nextLine();
            }
        }
    }

    private void printMainMenu() {
        System.out.println("========================================");
        System.out.println(" Tag 1: Listen – Mini-Challenge (Konsole)");
        System.out.println("========================================");
        System.out.println("1 - Level 1: Einsteiger (ToDo-Liste)");
        System.out.println("2 - Level 2: Fortgeschritten (Prioritäten & Undo)");
        System.out.println("3 - Level 3: Profi (SimpleList-Implementierung)");
        System.out.println("0 - Beenden");
        System.out.print("Auswahl: ");
    }

    /**
     * Level 1 – Einfache ToDo-Liste mit zwei ArrayLists (offen / erledigt).
     */
    private void runLevel1Demo() {
        System.out.println("--- Level 1 – Einsteiger: ToDo-Liste ---");

        TodoListService service = new TodoListService();

        System.out.println("Füge einige Aufgaben hinzu (ohne Priorität, Standard = MEDIUM)...");
        service.addTask("Java-Artikel lesen");
        service.addTask("Mini-Challenge nachbauen");
        service.addTask("Kaffee holen");

        printLists(service);

        System.out.println();
        System.out.println("Markiere die zweite Aufgabe (Index 1) als erledigt...");
        service.completeTask(1);
        printLists(service);

        System.out.println();
        System.out.println("Entferne die erste offene Aufgabe (Index 0) komplett...");
        service.removeOpenTask(0);
        printLists(service);
    }

    /**
     * Level 2 – Prioritäten, Sortierung und Undo-Funktion.
     */
    private void runLevel2Demo() {
        System.out.println("--- Level 2 – Fortgeschritten: Prioritäten & Undo ---");

        TodoListService service = new TodoListService();

        System.out.println("Füge Aufgaben mit unterschiedlichen Prioritäten hinzu...");
        service.addTask("Logging verbessern", Priority.LOW);
        service.addTask("Bug in Zahlungslogik fixen", Priority.HIGH);
        service.addTask("Refactoring des Services", Priority.MEDIUM);
        service.addTask("Dokumentation ergänzen", Priority.LOW);

        System.out.println();
        System.out.println("Offene Aufgaben (Einfüge-Reihenfolge):");
        printList("Open", service.getOpenTasks());

        System.out.println();
        System.out.println("Offene Aufgaben (sortiert nach Priorität HIGH > MEDIUM > LOW):");
        List<TodoItem> sorted = service.getOpenTasksSortedByPriority();
        printList("Open (sortiert)", sorted);

        System.out.println();
        System.out.println("Markiere Aufgabe mit Index 1 als erledigt...");
        service.completeTask(1);
        printLists(service);

        System.out.println();
        System.out.println("Mache die letzte Aktion mit undoLastAction() rückgängig...");
        boolean undone = service.undoLastAction();
        System.out.println("Undo erfolgreich? " + undone);
        printLists(service);

        System.out.println();
        System.out.println("Entferne offene Aufgabe mit Index 0 und führe dann erneut Undo aus...");
        service.removeOpenTask(0);
        printLists(service);

        System.out.println();
        System.out.println("Undo der letzten Aktion...");
        undone = service.undoLastAction();
        System.out.println("Undo erfolgreich? " + undone);
        printLists(service);
    }

    /**
     * Level 3 – Nutzung der eigenen SimpleList-Implementierung.
     *
     * Die eigentliche Implementierung befindet sich im Package
     * de.ibb.collections.todo.simplelist.
     */
    private void runLevel3Demo() {
        System.out.println("--- Level 3 – Profi: Eigene SimpleList-Implementierung ---");
        System.out.println("Siehe Klasse SimpleArrayList im Package simplelist für die Implementierung.");
        System.out.println("Eine kleine Demo findest du in SimpleListTestMain.");
    }

    private void printLists(TodoListService service) {
        printList("Open", service.getOpenTasks());
        printList("Done", service.getCompletedTasks());
    }

    private void printList(String title, List<TodoItem> items) {
        System.out.println(title + " (" + items.size() + "):");
        if (items.isEmpty()) {
            System.out.println("  <leer>");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println("  " + i + ": " + items.get(i));
            }
        }
    }
}

