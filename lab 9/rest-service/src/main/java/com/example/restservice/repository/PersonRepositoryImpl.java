package com.example.restservice.repository;

import com.example.restservice.entity.Person;
import com.example.restservice.exceptions.PersonExistsException;
import com.example.restservice.exceptions.PersonNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final List<Person> persons;

    public PersonRepositoryImpl() {
        persons = new ArrayList<>();
        persons.add(new Person(1, "John", 20, "john@mail.com"));
        persons.add(new Person(2, "Jane", 25, "jane@mail.com"));
        persons.add(new Person(3, "Max", 30, "max@mail.com"));
    }

    @Override
    public List<Person> getAllPersons() {
        return persons;
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person updatePerson(int id, Person person) throws PersonNotFoundException {
        return persons.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(p -> {
                    p.setId(person.getId());
                    p.setName(person.getName());
                    p.setAge(person.getAge());
                    p.setEmail(person.getEmail());
                    return p;
                })
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFoundException {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .map(persons::remove)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person addPerson(Person person) throws PersonExistsException {
        checkIfPersonAlreadyExists(person);
        persons.add(person);
        return person;
    }

    private void checkIfPersonAlreadyExists(Person person) {
        persons.stream()
                .filter(p -> p.getId() == person.getId())
                .findFirst()
                .ifPresent(p -> {
                    throw new PersonExistsException(p.getId());
                });
    }
}
