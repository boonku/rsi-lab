package com.example.restservice.controller;

import com.example.restservice.entity.Person;
import com.example.restservice.exceptions.PersonExistsException;
import com.example.restservice.exceptions.PersonNotFoundException;
import com.example.restservice.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/persons")
public class PersonController {
    private final PersonRepository personRepository;
    private final Logger log = LoggerFactory.getLogger(PersonController.class);

    @GetMapping()
    public CollectionModel<EntityModel<Person>> getPersons() {
        log.info("GET /persons");
        List<EntityModel<Person>> people = personRepository.getAllPeople()
                .stream()
                .map(PersonController::mapPersonToEntityModel)
                .toList();
        return CollectionModel.of(people, linkTo(methodOn(PersonController.class).getPersons()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Person> getPerson(@PathVariable int id) {
        log.info("GET /persons/{}", id);
        try {
            Person person = personRepository.getPerson(id);
            return mapPersonToEntityModel(person);
        } catch (PersonNotFoundException e) {
            log.warn("GET /persons/{} EXCEPTION", id);
            throw e;
        }
    }

    @PostMapping()
    public EntityModel<Person> addPerson(@RequestBody Person person) {
        log.info("POST /persons");
        try {
            Person addedPerson = personRepository.addPerson(person);
            return mapPersonToEntityModel(addedPerson);
        } catch (PersonExistsException e) {
            log.warn("POST /persons EXCEPTION");
            throw e;
        }
    }

    @PutMapping("/{id}")
    public EntityModel<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        log.info("PUT /persons");
        try {
            Person updatedPerson = personRepository.updatePerson(id, person);
            return mapPersonToEntityModel(updatedPerson);
        } catch (PersonNotFoundException e) {
            log.warn("PUT /persons EXCEPTION");
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        log.info("DELETE /persons/{}", id);
        try {
            personRepository.deletePerson(id);
            return ResponseEntity.noContent().build();
        } catch (PersonNotFoundException e) {
            log.warn("DELETE /persons/{} EXCEPTION", id);
            throw e;
        }
    }

    private static EntityModel<Person> mapPersonToEntityModel(Person person) {
        return EntityModel.of(person,
                linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).deletePerson(person.getId())).withRel("delete"),
                linkTo(methodOn(PersonController.class).getPersons()).withRel("list all"));
    }

}
