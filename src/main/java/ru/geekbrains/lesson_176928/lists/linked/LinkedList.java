package ru.geekbrains.lesson_176928.lists.linked;

import ru.geekbrains.lesson_176928.lists.List;

public interface LinkedList<T> extends List<T> {
    void insertFirst(T value);
    T removeFirst();
    T getFirst();
    T get(int index);
    void remove(int index);
    void remove(T value);
    boolean contains(T value);
    int size();
    boolean isEmpty();
    String toString();
}
