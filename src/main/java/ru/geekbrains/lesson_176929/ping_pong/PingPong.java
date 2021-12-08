package ru.geekbrains.lesson_176929.ping_pong;

public class PingPong {
    static private final Object monitor = new Object();
    private final String message;

    public PingPong(String message) {
        this.message = message;
    }

    public void start() {
        while (true) {
            synchronized (monitor) {
                try {
                    wait(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.message);
                monitor.notify();
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
