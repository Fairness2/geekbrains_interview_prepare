package ru.geekbrains.lesson_176931.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson_176931.models.Student;

import javax.persistence.Entity;
import java.util.List;

public class EntityRepository<T> {
    private final SessionFactory factory;
    private final Class<T> tClass;


    public EntityRepository(Class<T> tClass) {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        this.tClass = tClass;
    }

    public void preDestroy() {
        factory.close();
    }

    public T add(T entity) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();

        return entity;
    }

    public T delete(Integer id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        T entity = session.get(tClass, id);
        session.delete(entity);
        session.getTransaction().commit();

        return entity;
    }

    public List<T> getAll() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<T> list = session.createQuery("from " + tClass.getSimpleName()).getResultList();
        session.getTransaction().commit();

        return list;
    }

    public T getOne(Integer id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        T entity = session.get(tClass, id);
        session.getTransaction().commit();

        return entity;
    }

    public T create(T entity) {
        return this.add(entity);
    }

    public T update(T entity) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        return entity;
    }
}
