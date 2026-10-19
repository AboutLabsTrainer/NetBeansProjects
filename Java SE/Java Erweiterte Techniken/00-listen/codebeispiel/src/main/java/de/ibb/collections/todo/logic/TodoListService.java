package de.ibb.collections.todo.logic;

import de.ibb.collections.todo.model.Priority;
import de.ibb.collections.todo.model.TodoItem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

/**
 * Zentrale Logik für die ToDo-Listen-Mini-Challenge.
 *
 * - Level 1: offene und erledigte Aufgaben mit ArrayList verwalten
 * - Level 2: Prioritäten, Sortierung und Undo-Funktion
 */
public class TodoListService {

    private final List<TodoItem> openTasks = new ArrayList<>();
    private final List<TodoItem> completedTasks = new ArrayList<>();

    // Für Level 2 – Undo-Unterstützung (Stack der letzten Aktionen)
    private final Deque<UndoEntry> undoStack = new ArrayDeque<>();

    /**
     * Fügt eine neue Aufgabe mit Standard-Priorität (MEDIUM) hinzu.
     * Entspricht Level 1 der Mini-Challenge.
     */
    public void addTask(String description) {
        addTask(description, Priority.MEDIUM);
    }

    /**
     * Fügt eine neue Aufgabe mit expliziter Priorität hinzu (Level 2).
     */
    public void addTask(String description, Priority priority) {
        TodoItem item = new TodoItem(description, priority);
        int index = openTasks.size();
        openTasks.add(item);
        pushUndo(UndoType.ADD_OPEN, item, index, ListKind.OPEN);
    }

    /**
     * Gibt eine unveränderliche Sicht auf die offenen Aufgaben zurück.
     */
    public List<TodoItem> getOpenTasks() {
        return List.copyOf(openTasks);
    }

    /**
     * Gibt eine unveränderliche Sicht auf die erledigten Aufgaben zurück.
     */
    public List<TodoItem> getCompletedTasks() {
        return List.copyOf(completedTasks);
    }

    /**
     * Verschiebt eine offene Aufgabe (per Index) in die Liste der erledigten Aufgaben.
     * Entspricht „Speichere die erledigten Tasks in einer zweiten Liste“ (Level 1).
     *
     * @param index Index in der offenen Liste
     * @return true, wenn erfolgreich; false bei ungültigem Index
     */
    public boolean completeTask(int index) {
        if (!isValidIndex(index, openTasks)) {
            return false;
        }
        TodoItem item = openTasks.remove(index);
        completedTasks.add(item);
        pushUndo(UndoType.COMPLETE, item, index, ListKind.OPEN);
        return true;
    }

    /**
     * Entfernt eine offene Aufgabe dauerhaft (ohne sie als erledigt zu markieren).
     *
     * @param index Index in der offenen Liste
     * @return true, wenn erfolgreich; false bei ungültigem Index
     */
    public boolean removeOpenTask(int index) {
        if (!isValidIndex(index, openTasks)) {
            return false;
        }
        TodoItem item = openTasks.remove(index);
        pushUndo(UndoType.REMOVE_OPEN, item, index, ListKind.OPEN);
        return true;
    }

    /**
     * Sortiert die offene Liste nach Priorität (HIGH &gt; MEDIUM &gt; LOW).
     * Entspricht Level 2 der Mini-Challenge.
     */
    public void sortOpenTasksByPriority() {
        openTasks.sort(Comparator.comparing(TodoItem::getPriority));
    }

    /**
     * Gibt eine neue Liste der offenen Aufgaben sortiert nach Priorität zurück.
     * Die interne Reihenfolge der offenen Liste bleibt unverändert.
     */
    public List<TodoItem> getOpenTasksSortedByPriority() {
        List<TodoItem> copy = new ArrayList<>(openTasks);
        copy.sort(Comparator.comparing(TodoItem::getPriority));
        return Collections.unmodifiableList(copy);
    }

    /**
     * Macht die letzte Änderung an den Listen rückgängig, sofern vorhanden.
     *
     * @return true, wenn etwas rückgängig gemacht wurde, sonst false
     */
    public boolean undoLastAction() {
        UndoEntry entry = undoStack.pollLast();
        if (entry == null) {
            return false;
        }

        switch (entry.type) {
            case ADD_OPEN:
                undoAddOpen(entry);
                break;
            case COMPLETE:
                undoComplete(entry);
                break;
            case REMOVE_OPEN:
                undoRemoveOpen(entry);
                break;
            default:
                // aktuell keine weiteren Typen
                return false;
        }
        return true;
    }

    /**
     * Leert den Undo-Stack.
     */
    public void clearUndoHistory() {
        undoStack.clear();
    }

    private void undoAddOpen(UndoEntry entry) {
        // Versuche an ursprünglicher Position zu entfernen, ansonsten via equals
        if (entry.index >= 0 && entry.index < openTasks.size()
                && openTasks.get(entry.index).equals(entry.item)) {
            openTasks.remove(entry.index);
        } else {
            openTasks.remove(entry.item);
        }
    }

    private void undoComplete(UndoEntry entry) {
        // Entferne aus completed und füge an ursprünglicher Position in openTasks ein
        int posInCompleted = completedTasks.lastIndexOf(entry.item);
        if (posInCompleted >= 0) {
            completedTasks.remove(posInCompleted);
        }
        int insertIndex = Math.min(Math.max(entry.index, 0), openTasks.size());
        openTasks.add(insertIndex, entry.item);
    }

    private void undoRemoveOpen(UndoEntry entry) {
        int insertIndex = Math.min(Math.max(entry.index, 0), openTasks.size());
        openTasks.add(insertIndex, entry.item);
    }

    private void pushUndo(UndoType type, TodoItem item, int index, ListKind listKind) {
        Objects.requireNonNull(item, "item must not be null");
        undoStack.addLast(new UndoEntry(type, item, index, listKind));
    }

    private boolean isValidIndex(int index, List<?> list) {
        return index >= 0 && index < list.size();
    }

    private enum UndoType {
        ADD_OPEN,
        COMPLETE,
        REMOVE_OPEN
    }

    private enum ListKind {
        OPEN,
        COMPLETED
    }

    private static final class UndoEntry {
        private final UndoType type;
        private final TodoItem item;
        private final int index;
        private final ListKind listKind;

        private UndoEntry(UndoType type, TodoItem item, int index, ListKind listKind) {
            this.type = Objects.requireNonNull(type);
            this.item = Objects.requireNonNull(item);
            this.index = index;
            this.listKind = Objects.requireNonNull(listKind);
        }
    }
}

