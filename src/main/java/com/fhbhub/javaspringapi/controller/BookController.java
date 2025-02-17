package com.fhbhub.javaspringapi.controller;

import com.fhbhub.javaspringapi.controller.swagger.BookSwagger;
import com.fhbhub.javaspringapi.data.dto.BookDTO;
import com.fhbhub.javaspringapi.service.BookService;
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
@RequestMapping(value = "/api/book/v1", produces = {"application/json"})
public class BookController implements BookSwagger {

    private final BookService bookService;

    @Override
    public ResponseEntity<BookDTO> create(BookDTO bookDTO) {
        var response = bookService.create(bookDTO);
        addHateoas(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<List<BookDTO>> findAll() {
        var response = bookService.findAll();
        response.forEach(this::addHateoas);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BookDTO> findById(Long id) {
        var response = bookService.findById(id);
        addHateoas(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BookDTO> update(BookDTO bookDTO) {
        var response = bookService.update(bookDTO);
        addHateoas(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void addHateoas(BookDTO bookDTO) {
        bookDTO.add(linkTo(methodOn(PersonController.class).findById(bookDTO.getKey())).withSelfRel());
    }
}
