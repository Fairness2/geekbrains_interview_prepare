package ru.geekbrains.lesson_176928.random;

import java.util.Iterator;
import java.util.Random;

public class RandomIterator implements Iterator<Integer> {
    private int size;
    private int current;
    private int bottom;
    private int top;

    public RandomIterator(int size, int bottom, int top) {
        this.size = size;
        this.bottom = bottom;
        this.top = top;
        current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < size;
    }

    @Override
    public Integer next() {
        current++;
        Random random = new Random();
        return random.nextInt(top - bottom + 1) + bottom;
    }

    public void reset() {
        current = 0;
    }
}
