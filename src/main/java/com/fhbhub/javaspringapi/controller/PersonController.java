package com.fhbhub.javaspringapi.controller;

import com.fhbhub.javaspringapi.controller.swagger.PersonSwagger;
import com.fhbhub.javaspringapi.data.dto.PersonDTO;
import com.fhbhub.javaspringapi.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/person/v1", produces = {"application/json"})
public class PersonController implements PersonSwagger {

    private final PersonService service;

    @Override
    public ResponseEntity<PersonDTO> create(PersonDTO personDTO) {
        var response = service.create(personDTO);
        addHateoas(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<PersonDTO>> findAll() {
        var response = service.findAll();
        response.forEach(this::addHateoas);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<PersonDTO> findById(Long id) {
        var response = service.findById(id);
        addHateoas(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<PersonDTO> update(PersonDTO personDTO) {
        var response = service.update(personDTO);
        addHateoas(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void addHateoas(PersonDTO personDTO) {
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel().withType("GET"));
        personDTO.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        personDTO.add(linkTo(methodOn(PersonController.class).create(personDTO)).withRel("create").withType("POST"));
        personDTO.add(linkTo(methodOn(PersonController.class).update(personDTO)).withRel("update").withType("PUT"));
        personDTO.add(linkTo(methodOn(PersonController.class).delete(personDTO.getKey())).withRel("delete").withType("DELETE"));
    }
}
