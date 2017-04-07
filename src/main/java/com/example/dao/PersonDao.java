package com.example.dao;        //interfejs który tworzy listę użytkowników w DB

import com.example.model.Person;

import java.util.List;

public interface PersonDao {
    void save (Person person);
    List<Person> findAll();
    List<Person> findSurname(String surname);
    void remove(Person person);
    void remove(Long id);
}
