package com.example.restservice.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("Specified person does not exist");
    }

    public PersonNotFoundException(int id) {
        super("Person with id '" + id + " 'does not exist");
    }
}
