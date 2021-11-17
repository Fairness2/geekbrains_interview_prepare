package ru.geekbrains.lesson_176927.figures;

public class Triangle extends Figure {
    private double sideOne;
    private double sideTwo;
    private double angle;

    public Triangle(double sideOne, double sideTwo, double angle) {
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        this.angle = angle;
    }

    @Override
    public double area() {
        return 0.5 * sideOne * sideTwo * Math.sin(angle);
    }
}
