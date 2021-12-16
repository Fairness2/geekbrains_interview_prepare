package ru.geekbrains.lesson_176931.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson_176931.models.Student;

import java.util.List;

public class StudentRepository {
    private final SessionFactory factory;


    public StudentRepository() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public void preDestroy() {
        factory.close();
    }

    public boolean add(Student student) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(student);
        session.getTransaction().commit();

        return student.getId() != null;
    }

    public boolean delete(Integer id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.delete(student);
        session.getTransaction().commit();

        return student.getId() != null;
    }

    public List<Student> getAll() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Student> list = session.createQuery("from Student").getResultList();
        session.getTransaction().commit();

        return list;
    }

    public Student getOne(Integer id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.getTransaction().commit();

        return student;
    }

    public Student create(String name, String mark) {
        Student student = new Student();
        student.setName(name);
        student.setMark(mark);

        return this.add(student) ? student : null;
    }

    public Student update(Integer id, String name, String mark) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        student.setName(name);
        student.setMark(mark);
        session.saveOrUpdate(student);
        session.getTransaction().commit();
        return student;
    }
}
