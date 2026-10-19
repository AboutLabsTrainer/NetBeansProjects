package de.ibb.collections.todo.simplelist;

/**
 * Vereinfachtes Listen-Interface für die Mini-Challenge (Level 3).
 *
 * Die Implementierung verwendet intern KEINE java.util.ArrayList,
 * sondern ein eigenes Array-Backing.
 */
public interface SimpleList<E> extends Iterable<E> {

    void add(E element);

    E get(int index);

    E remove(int index);

    int size();

    boolean contains(E element);
}

