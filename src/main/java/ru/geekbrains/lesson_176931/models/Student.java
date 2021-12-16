package ru.geekbrains.lesson_176931.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "mark")
    private String mark;

    public Student(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return String.format("Student: {%nID: %s%nName: %s%nMark: %s%n}%n", id, name, mark);
    }

}
