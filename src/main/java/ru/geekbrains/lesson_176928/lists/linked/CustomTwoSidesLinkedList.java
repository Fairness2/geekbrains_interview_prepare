package ru.geekbrains.lesson_176928.lists.linked;

public class CustomTwoSidesLinkedList<T> extends CustomLinkedList<T> implements TwoSidesLinkedList<T> {
    Node<T> last;

    @Override
    public void insertFirst(T value) {
        super.insertFirst(value);
        if (size == 1) {
            last = first;
        }
        else {
            first.getNext().setPrevious(first);
        }
    }

    @Override
    public T removeFirst() {
        T value = super.removeFirst();
        if (isEmpty()) {
            last = null;
        }
        else {
            first.setPrevious(null);
        }

        return value;
    }

    @Override
    public void remove(T value) {
        Node<T> current = first;
        Node<T> previous = null;
        while (current != null) {
            if (current.getItem().equals(value)) {
                break;
            }
            previous = current;
            current = current.getNext();
        }

        if (current == null) {
            return;
        } else if (current == first) {
            removeFirst();
            return;
        } else if (current == last) {
            last = previous;
            previous.setNext(null);;
        } else {
            if (previous != null) {
                previous.setNext(current.getNext());
            }
            current.getNext().setPrevious(previous);
        }

        current.setNext(null);
        current.setPrevious(null);
        size--;
    }

    @Override
    public void insertLast(T value) {
        Node<T> newNode = new Node<>(value, null, last);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    @Override
    public T getLast() {
        return last != null ? last.getItem() : null;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> current = last;
        if (first == last) { //Сравниваем ссылки
            first = null;
        }
        last = last.getPrevious();
        if (last != null) {
            last.setNext(null);
        }
        current.setPrevious(null);
        size--;
        return current.getItem();
    }
}
