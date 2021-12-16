package ru.geekbrains.lesson_176931;

import ru.geekbrains.lesson_176931.models.Student;
import ru.geekbrains.lesson_176931.repositories.EntityRepository;
import ru.geekbrains.lesson_176931.repositories.StudentRepository;

public class Main {
    public static void main(String[] args) {
        //init();
        //test();
        test2();
    }

    private static void init() {
        StudentRepository repository = new StudentRepository();
        for(int i = 0; i < 1000; i++) {
            repository.create(String.format("Student %s", i), "Mark");
        }
        repository.preDestroy();
        System.out.println("1000 students inserted");
    }

    private static void test() {
        StudentRepository repository = new StudentRepository();
        Student student = repository.create("New student", "Mark 1");
        System.out.println("Created student: ");
        System.out.println(student.toString());

        student = repository.update(student.getId(), "Updated Student", student.getMark());
        System.out.println("Updated student: ");
        System.out.println(student.toString());

        student = repository.getOne(student.getId());
        System.out.println("Selected student: ");
        System.out.println(student.toString());

        boolean res = repository.delete(student.getId());
        System.out.println("User delete: " + res);
    }

    private static void test2() {
        EntityRepository<Student> repository = new EntityRepository<>(Student.class);
        Student student = repository.create(new Student("New student", "Mark 1"));
        System.out.println("Created student: ");
        System.out.println(student.toString());

        student.setName("Updated Student");
        student = repository.update(student);
        System.out.println("Updated student: ");
        System.out.println(student.toString());

        student = repository.getOne(student.getId());
        System.out.println("Selected student: ");
        System.out.println(student.toString());

        student = repository.delete(student.getId());
        System.out.println("Deleted user: ");
        System.out.println(student.toString());
    }
}
