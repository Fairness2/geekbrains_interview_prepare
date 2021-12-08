package ru.geekbrains.lesson_176928.lists.array;

import ru.geekbrains.lesson_176928.lists.linked.CustomLinkedList;
import ru.geekbrains.lesson_176928.lists.linked.LinkedIterator;
import ru.geekbrains.lesson_176928.lists.linked.Node;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomArrayList<T extends Comparable<? super T>> implements ArrayList<T> {
    private static final int DEFAULT_CNT = 16;
    private T[] innerArray;
    private int size;
    private boolean sorted = false;

    public CustomArrayList() {
        this(DEFAULT_CNT);
    }

    @SuppressWarnings("unchecked")
    public CustomArrayList(int cnt) {
        innerArray = (T[]) new Comparable[cnt];
        size = 0;
    }

    //O(n)
    @Override
    public void remove(int index) {
        checkIndex(index);
        sorted = false;
        System.arraycopy(innerArray, index + 1, innerArray, index, size - index - 1);
        innerArray[--size] = null;
    }

    //O(1) -> O(n)
    @Override
    public void add(T value) {
        checkAndIncrease();
        sorted = false;
        innerArray[size++] = value;
    }

    //O(1) -> O(2n)?
    @Override
    public void add(T value, int index) {
        checkIndex(index);
        checkAndIncrease();
        sorted = false;
        if (index == 0) {
            System.arraycopy(innerArray, 0, innerArray, 1, innerArray.length - 2);
            innerArray[index] = value;
            size++;
        }
        else if (index == size) {
            innerArray[size++] = value;
        }
        else {
            T[] secondPart = Arrays.copyOfRange(innerArray, index, size);
            innerArray[index] = value;
            System.arraycopy(secondPart, 0, innerArray, index + 1, secondPart.length);
            size++;
        }
    }

    //O(1)
    @Override
    public T get(int index) {
        checkIndex(index);
        return innerArray[index];
    }

    //O(log n) -> O(n)
    @Override
    public int find(T value) {
        if (sorted) {
            return binarySearch(value);
        }
        else {
            return linearSearch(value);
        }
    }

    private void checkAndIncrease() {
        if (innerArray.length == size) {
            innerArray = Arrays.copyOf(innerArray, getNewSize());
        }
    }

    private int getNewSize() {
        return size == 0 ? DEFAULT_CNT : size * 2;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            String errorMsg = String.format("Индекс за пределами массива, укажите индекс от 0 до %d", size - 1);
            throw new IndexOutOfBoundsException(errorMsg);
        }
    }

    private int linearSearch(T value) {
        for(int i = 0; i < size; i++) {
            if (innerArray[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private int binarySearch(T value) {
        int index;
        int res;
        int indexStart = 0;
        int indexEnd = size - 1;
        while (indexStart <= indexEnd) {
            index = (indexEnd - indexStart) / 2;
            res = value.compareTo(innerArray[index]);
            if (res == 0) {
                return index;
            }
            else if (res == -1) {
                indexEnd = index - 1;
            }
            else {
                indexStart = index + 1;
            }
        }
        return -1;
    }

    private void swapElements(int indexA, int indexB) {
        T a = innerArray[indexA];
        innerArray[indexA] = innerArray[indexB];
        innerArray[indexB] = a;
    }

    //O(n*2)
    @Override
    public void bubbleSort() {
        for (int i = 0; i < size - 1; i ++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (innerArray[j].compareTo(innerArray[j + 1]) > 0) {
                    swapElements(j, j + 1);
                }
            }
        }
        sorted = true;
    }

    // O(n) -> O(n^2)
    @Override
    public void selectSort() {
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (innerArray[j].compareTo(innerArray[min]) < 0) {
                    min = j;
                }
            }
            swapElements(i, min);
        }
        sorted = true;
    }

    // O(n) -> O(n^2)
    @Override
    public void insertSort() {
        for (int i = 1; i < size; i++) {
            T value = innerArray[i];
            int index = i;
            while (index > 0 && innerArray[index - 1].compareTo(value) >= 0) {
                innerArray[index] = innerArray[index - 1];
                index--;
            }
            innerArray[index] = value;
        }
        sorted = true;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < size; i++) {
            sb.append(innerArray[i]).append(' ');
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    protected class Iterator implements java.util.Iterator<T> {
        CustomArrayList<T> list;
        int index;

        public Iterator() {
            index = 0;
            list = CustomArrayList.this;
            reset();
        }

        @Override
        public boolean hasNext() {
            return index != (list.length() - 1);
        }

        @Override
        public T next() {
            index++;
            return list.get(index);
        }

        public void reset() {
            index = 0;
        }
    }
}
