package ru.geekbrains.lesson_176928.lists.linked;

public interface TwoSidesLinkedList<T> extends LinkedList<T> {
    void insertLast(T value);
    T getLast();
    T removeLast();
}
