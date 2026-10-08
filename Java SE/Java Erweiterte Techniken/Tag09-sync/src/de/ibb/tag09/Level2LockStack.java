package de.ibb.tag09;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-sicherer Stack auf Basis eines ReentrantLock.
 */
public class Level2LockStack<T> {

    private final Deque<T> stack = new ArrayDeque<>();
    private final ReentrantLock lock = new ReentrantLock();

    public void push(T element) {
        lock.lock();
        try {
            stack.push(element);
        } finally {
            lock.unlock();
        }
    }

    public T pop() {
        lock.lock();
        try {
            return stack.pollFirst();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return stack.size();
        } finally {
            lock.unlock();
        }
    }
}

