package com.fhbhub.javaspringapi.controller;

import com.fhbhub.javaspringapi.controller.swagger.PersonSwagger;
import com.fhbhub.javaspringapi.data.vo.PersonVO;
import com.fhbhub.javaspringapi.services.PersonServices;
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

    private final PersonServices service;

    @Override
    public ResponseEntity<List<PersonVO>> findAll() {
        var peopleVO = service.findAll();
        peopleVO.forEach(this::addHateoas);
        return ResponseEntity.status(HttpStatus.OK).body(peopleVO);
    }

    @Override
    public ResponseEntity<PersonVO> findById(Long id) {
        var personVO = service.findById(id);
        addHateoas(personVO);
        return ResponseEntity.status(HttpStatus.OK).body(personVO);
    }

    @Override
    public ResponseEntity<PersonVO> create(PersonVO person) {
        var personVO = service.create(person);
        addHateoas(personVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personVO);
    }

    @Override
    public ResponseEntity<PersonVO> update(PersonVO person) {
        var personVO = service.update(person);
        addHateoas(personVO);
        return ResponseEntity.status(HttpStatus.OK).body(personVO);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    private void addHateoas(PersonVO personVO) {
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
    }
}
