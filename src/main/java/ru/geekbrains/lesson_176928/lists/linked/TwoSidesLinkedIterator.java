package ru.geekbrains.lesson_176928.lists.linked;

public interface TwoSidesLinkedIterator<T> extends LinkedIterator<T> {
    boolean hasPrevious();
    boolean getPrevious();
}
