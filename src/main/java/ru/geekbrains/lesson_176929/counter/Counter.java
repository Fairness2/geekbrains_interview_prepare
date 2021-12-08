package ru.geekbrains.lesson_176929.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int counter;
    private final Lock lock;

    public Counter() {
        this.counter = 0;
        this.lock = new ReentrantLock();
    }

    public int getCounter() {
        return counter;
    }

    public void increase() {
        this.lock.lock();
        this.counter++;
        System.out.println(this.counter);
        this.lock.unlock();
    }
}
