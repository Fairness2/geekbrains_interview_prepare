package ru.geekbrains.lesson_176927;

import ru.geekbrains.lesson_176927.figures.Circle;
import ru.geekbrains.lesson_176927.figures.Figure;
import ru.geekbrains.lesson_176927.figures.Square;
import ru.geekbrains.lesson_176927.figures.Triangle;
import ru.geekbrains.lesson_176927.user.User;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        createUser();
        calculateAreas();
    }

    private static void createUser() {
        User user = User.builder()
                .firstName("Carl")
                .lastName("Johnson")
                .middleName("John")
                .country("USA")
                .address("Grove Street")
                .phone("8-800-555-35-55")
                .age(21)
                .gender("Male")
                .getUser();
        System.out.println(user.toString());
    }

    private static void calculateAreas() {
        Figure[] figures = new Figure[] {
                new Circle(2.5),
                new Square(5),
                new Triangle(2, 5, 1.5708)
        };

        Arrays.stream(figures).forEach((item) -> System.out.println(item.area()));
    }
}
