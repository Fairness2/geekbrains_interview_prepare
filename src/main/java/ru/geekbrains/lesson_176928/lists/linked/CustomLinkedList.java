package ru.geekbrains.lesson_176928.lists.linked;

import java.util.NoSuchElementException;

public class CustomLinkedList<T> implements LinkedList<T> {
    protected int size;
    protected Node<T> first;

    public CustomLinkedList() {
        size = 0;
    }

    //O(1)
    @Override
    public void insertFirst(T value) {
        first = new Node<>(value, first);
        size++;
    }

    //O(1)
    @Override
    public T removeFirst() {
        Node<T> oldFirst = first;
        first = first.getNext();
        oldFirst.setNext(null);
        size--;
        return oldFirst.getItem();
    }

    @Override
    public T get(int index) {
        if (index >= size() || index < 0) {
            throw new NullPointerException("Out of bound list size");
        }
        LinkedIterator<T> iterator = (LinkedIterator<T>) iterator();
        for (int i = 0; i <= index; i++) {
            T val = iterator.next();
            if (i == index) {
                return val;
            }
        }
        return null;
    }

    //O(n)
    @Override
    public void remove(int index) throws NullPointerException {
        if (index >= size() || index < 0) {
            throw new NullPointerException("Out of bound list size");
        }
        LinkedIterator<T> iterator = (LinkedIterator<T>) iterator();
        for (int i = 0; i <= index; i++) {
            iterator.next();
            if (i == index) {
                iterator.remove();
                break;
            }
        }
    }

    //O(n)
    @Override
    public void remove(T value) {
        LinkedIterator<T> iterator = (LinkedIterator<T>) iterator();
        while (iterator.hasNext()) {
            if (value.equals(iterator.next())) {
                iterator.remove();
                return;
            }
        }
    }

    //O(n)
    @Override
    public boolean contains(T value) {
        LinkedIterator<T> iterator = (LinkedIterator<T>) iterator();
        while (iterator.hasNext()) {
            if (value.equals(iterator.next())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (T item: this) {
            sb.append(item).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public T getFirst() {
        return first.getItem();
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    protected class Iterator implements LinkedIterator<T> {
        CustomLinkedList<T> list;
        Node<T> current;
        Node<T> previous;

        public Iterator() {
            list = CustomLinkedList.this;
            reset();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            previous = current;
            current = current.getNext();
            return previous.getItem();
        }

        @Override
        public void reset() {
            current = list.first;
            previous = null;
        }

        @Override
        public void insertBefore(T value) {
            Node<T> newNode = new Node<>(value, current);
            if (previous != null) {
                previous.setNext(newNode);
            }
            else {
                list.first = newNode;
                previous = newNode;
            }
        }

        @Override
        public void insertAfter(T value) {
            Node<T> newNode = new Node<>(value, null);
            if (!list.isEmpty()) {
                if (!hasNext()) {
                    current = newNode;
                    if (previous != null) {
                        previous.setNext(current);
                    }
                }
                else {
                    newNode.setNext(current.getNext());
                    current.setNext(newNode);
                }
            }
            else {
                list.first = newNode;
                current = newNode;
            }
        }

        @Override
        public void remove() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (previous != null) {
                previous.setNext(current.getNext());
            }
            Node<T> oldNode = current;
            current = current.getNext();
            oldNode.setNext(null);
        }
    }

}
