package ru.geekbrains.lesson_176928.lists.linked;

import java.util.Iterator;

public interface LinkedIterator<T> extends Iterator<T> {
    void reset();
    void insertBefore(T value);
    void insertAfter(T value);
}
