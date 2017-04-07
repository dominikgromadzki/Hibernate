package com.example.dao;                //metody które wysyłają zapytania do bazy

import com.example.model.Person;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by dominikgromadzki on 30.03.2017.
 */

@Transactional
@Component
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Override
    public List<Person> findAll() {
        Query query = entityManager.createQuery("SELECT p FROM Person p");
        return query.getResultList();
    }

    @Override
    public List<Person> findSurname(String surname) {
        Query query = entityManager.createQuery("SELECT p FROM Person p WHERE p.surname='" + surname + "'");
        return query.getResultList();
    }

    @Override
    public void remove(Person person) {
        entityManager.remove(person);
    }

    @Override
    public void remove(Long id) {
        Person person = entityManager.getReference(Person.class,id);     //getReference wyszukuje id w bazie
        entityManager.remove(person);
    }

}
