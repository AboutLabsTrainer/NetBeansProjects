package de.ibb.collections.todo.simplelist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Einfache Listen-Implementierung für die Mini-Challenge (Level 3).
 *
 * Verwendet intern ein dynamisch wachsendes Array, aber KEINE java.util.ArrayList.
 */
public class SimpleArrayList<E> implements SimpleList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public SimpleArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E removed = (E) elements[index];
        int elementsToMove = size - index - 1;
        if (elementsToMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, elementsToMove);
        }
        elements[--size] = null; // help GC
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            Object current = elements[i];
            if (Objects.equals(current, element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) elements[cursor++];
            }
        };
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= elements.length) {
            return;
        }
        int newCapacity = Math.max(elements.length * 2, minCapacity);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }
}

