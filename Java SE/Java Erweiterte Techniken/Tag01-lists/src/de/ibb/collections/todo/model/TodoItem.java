package de.ibb.collections.todo.model;

import java.util.Objects;

/**
 * Ein einzelner ToDo-Eintrag für die Mini-Challenge.
 */
public class TodoItem {

    private final String description;
    private final Priority priority;

    public TodoItem(String description) {
        this(description, Priority.MEDIUM);
    }

    public TodoItem(String description, Priority priority) {
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.priority = Objects.requireNonNull(priority, "priority must not be null");
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "[" + priority + "] " + description;
    }
}

