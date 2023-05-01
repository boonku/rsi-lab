package com.example.restservice.exceptions;

public class PersonExistsException extends RuntimeException {
    public PersonExistsException(int id) {
        super("Person with id '" + id + "' already exists");
    }
}
