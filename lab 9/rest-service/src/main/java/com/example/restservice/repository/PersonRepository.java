package com.example.restservice.repository;

import com.example.restservice.entity.Person;
import com.example.restservice.exceptions.PersonExistsException;
import com.example.restservice.exceptions.PersonNotFoundException;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPersons();

    Person getPerson(int id) throws PersonNotFoundException;

    Person updatePerson(int id, Person person) throws PersonNotFoundException;

    boolean deletePerson(int id) throws PersonNotFoundException;

    Person addPerson(Person person) throws PersonExistsException;
}
