package ru.geekbrains.lesson_176929;

import ru.geekbrains.lesson_176929.counter.Counter;
import ru.geekbrains.lesson_176929.ping_pong.PingPong;

public class Main {

    public static void main(String[] args) {
        //pingPong();
        counter();
    }

    private static void pingPong() {
        (new Thread(() -> (new PingPong("ping")).start())).start();
        (new Thread(() -> (new PingPong("pong")).start())).start();
    }

    private static void counter() {
        Counter counter = new Counter();
        (new Thread(() -> {
            while (true) {
                counter.increase();
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).start();
        (new Thread(() -> {
            while (true) {
                counter.increase();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).start();
        (new Thread(() -> {
            while (true) {
                counter.increase();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).start();
    }
}
