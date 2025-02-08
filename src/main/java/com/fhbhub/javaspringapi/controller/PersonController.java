package com.fhbhub.javaspringapi.controller;

import com.fhbhub.javaspringapi.data.vo.v1.PersonVO;
import com.fhbhub.javaspringapi.services.PersonServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/person/v1", produces = {"application/json"})
public class PersonController {

    private final PersonServices service;

    @GetMapping()
    public ResponseEntity<List<PersonVO>> findAll() {
        var peopleList = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(peopleList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonVO> findById(@PathVariable("id") Long id) {
        var person = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
        var createdPerson = service.create(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @PutMapping(consumes = {"application/json"})
    public ResponseEntity<PersonVO> update(@RequestBody PersonVO person) {
        var updatedPerson = service.update(person);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}