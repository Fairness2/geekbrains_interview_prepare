package ru.geekbrains.lesson_176928.lists.linked;

public class Node<T> implements LinkedNode<T>{
    private Node<T> previous;
    private Node<T> next;
    private T item;

    public Node(T item, Node<T> next) {
        this(item, next, null);
    }

    public Node(T item, Node<T> next, Node<T> previous) {
        this.item = item;
        this.next = next;
        this.previous = previous;
    }

    @Override
    public Node<T> getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(LinkedNode<T> previous) {
        this.previous = (Node<T>) previous;
    }

    @Override
    public Node<T> getNext() {
        return next;
    }

    @Override
    public void setNext(LinkedNode<T> next) {
        this.next = (Node<T>)next;
    }

    @Override
    public T getItem() {
        return item;
    }

    @Override
    public void setItem(T item) {
        this.item = item;
    }
}
