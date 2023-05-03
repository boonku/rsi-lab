package com.example.restservice.repository;

import com.example.restservice.entity.Person;
import com.example.restservice.exceptions.PersonExistsException;
import com.example.restservice.exceptions.PersonNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final List<Person> people;

    public PersonRepositoryImpl() {
        people = new ArrayList<>();
        people.add(new Person(1, "John", 20, "john@mail.com"));
        people.add(new Person(2, "Jane", 25, "jane@mail.com"));
        people.add(new Person(3, "Max", 30, "max@mail.com"));
    }

    @Override
    public List<Person> getAllPeople() {
        return people;
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person updatePerson(int id, Person person) throws PersonNotFoundException {
        return people.stream()
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
        return people.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .map(people::remove)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person addPerson(Person person) throws PersonExistsException {
        checkIfPersonAlreadyExists(person);
        people.add(person);
        return person;
    }

    private void checkIfPersonAlreadyExists(Person person) {
        people.stream()
                .filter(p -> p.getId() == person.getId())
                .findFirst()
                .ifPresent(p -> {
                    throw new PersonExistsException(p.getId());
                });
    }
}
