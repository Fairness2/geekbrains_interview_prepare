package ru.geekbrains.lesson_176928;


import ru.geekbrains.lesson_176928.lists.array.CustomArrayList;
import ru.geekbrains.lesson_176928.lists.linked.CustomTwoSidesLinkedList;
import ru.geekbrains.lesson_176928.lists.linked.LinkedIterator;
import ru.geekbrains.lesson_176928.random.RandomIterator;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //arrayApp();
        //linkIteratorApp();
        randomIteratorApp();
    }

    private static void arrayApp() {
        int size = 10000;
        CustomArrayList<Integer> arr1 = new CustomArrayList<>(size);
        randomizeArray(arr1, size);
        //System.out.println(arr1.toString());
        arr1.add(99, 3);
        //System.out.println(arr1.toString());
        //System.out.println(arr1.find(99));
        arr1.remove(3);
        //System.out.println(arr1.toString());

        CustomArrayList<Integer> arr2 = new CustomArrayList<>(size);
        CustomArrayList<Integer> arr3 = new CustomArrayList<>(size);
        randomizeArray(arr2, size);
        randomizeArray(arr3, size);

        long start = System.currentTimeMillis();
        arr1.bubbleSort();
        System.out.printf("Сортировка пузырьком %d мc.%n", (System.currentTimeMillis() - start));
        //System.out.println(arr1.toString());

        start = System.currentTimeMillis();
        arr2.insertSort();
        System.out.printf("Сортировка выбором %d мc.%n", (System.currentTimeMillis() - start));
        //System.out.println(arr2.toString());

        start = System.currentTimeMillis();
        arr3.insertSort();
        System.out.printf("Сортировка вставкой %d мc.%n", (System.currentTimeMillis() - start));
        //System.out.println(arr3.toString());
    }

    private static void randomizeArray(CustomArrayList<Integer> arr, int size) {
        Random random = new Random();
        int bound = size * 10;
        for (int i = 0; i < size; i++) {
            arr.add(random.nextInt(bound));
        }
    }

    private static void linkIteratorApp() {
        CustomTwoSidesLinkedList<Integer> list = new CustomTwoSidesLinkedList<>();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);
        list.insertFirst(5);
        StringBuilder sb = new StringBuilder();
        sb.append("Проверка форича: ");
        for (Integer i: list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
        LinkedIterator<Integer> iterator = (LinkedIterator<Integer>) list.iterator();
        sb = new StringBuilder();
        sb.append("\nПроверка прохода итератора: ");
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(" ");
        }
        System.out.println(sb.toString());
        System.out.println("Проверка вставки и удаления");
        iterator = (LinkedIterator<Integer>) list.iterator();
        iterator.next();
        iterator.next();
        iterator.insertBefore(7);
        iterator.insertAfter(6);
        iterator.next();
        iterator.next();
        iterator.remove();

        System.out.println(list.toString());
    }

    private static void randomIteratorApp() {
        RandomIterator iterator = new RandomIterator(5, 1, 10);
        while (iterator.hasNext()) {
            System.out.println((Integer) iterator.next());
        }
    }
}
