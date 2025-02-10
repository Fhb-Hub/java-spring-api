package com.fhbhub.javaspringapi.controller;

import com.fhbhub.javaspringapi.data.vo.PersonVO;
import com.fhbhub.javaspringapi.services.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/person/v1", produces = {"application/json"})
@Tag(name = "People", description = "Endpoint for people management")
public class PersonController {

    private final PersonServices service;

    @GetMapping()
    public ResponseEntity<List<PersonVO>> findAll() {
        var peopleVO = service.findAll();
        peopleVO.forEach(this::addHateoas);
        return ResponseEntity.status(HttpStatus.OK).body(peopleVO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonVO> findById(@PathVariable("id") Long id) {
        var personVO = service.findById(id);
        addHateoas(personVO);

        return ResponseEntity.status(HttpStatus.OK).body(personVO);
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
        var personVO = service.create(person);
        addHateoas(personVO);

        return ResponseEntity.status(HttpStatus.CREATED).body(personVO);
    }

    @PutMapping(consumes = {"application/json"})
    public ResponseEntity<PersonVO> update(@RequestBody PersonVO person) {
        var personVO = service.update(person);
        addHateoas(personVO);

        return ResponseEntity.status(HttpStatus.OK).body(personVO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.ok().build();
    }

    private void addHateoas(PersonVO personVO) {
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
    }
}