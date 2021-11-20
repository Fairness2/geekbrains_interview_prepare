package ru.geekbrains.lesson_176927.code;

abstract class Car {
    public Engine engine; //Зачем паблик, если есть геттер и сеттер, логично закрыть за модификатором private
    private String color;
    private String name;


    protected void start() { //Если вынесли уже функцию движения и функцию остановки в интерфейсы, то логично и эту функцию унести в интерфейс
        System.out.println("Car starting");
    }

    abstract void open(); // Эту тоже можно унести в интерфейс, и например, к этому классу уже подцепить эти два интерфейса, возможно нужно состояние и обратную функцию close

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
