package com.fhbhub.javaspringapi.controller;

import com.fhbhub.javaspringapi.controller.swagger.BookSwagger;
import com.fhbhub.javaspringapi.data.vo.BookVO;
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
    public ResponseEntity<BookVO> create(BookVO book) {
        var bookVO = bookService.create(book);
        addHateoas(bookVO);
        return ResponseEntity.status(HttpStatus.OK).body(bookVO);
    }

    @Override
    public ResponseEntity<List<BookVO>> findAll() {
        var booksVO = bookService.findAll();
        booksVO.forEach(this::addHateoas);
        return ResponseEntity.status(HttpStatus.OK).body(booksVO);
    }

    @Override
    public ResponseEntity<BookVO> findById(Long id) {
        var bookVO = bookService.findById(id);
        addHateoas(bookVO);
        return ResponseEntity.status(HttpStatus.OK).body(bookVO);
    }

    @Override
    public ResponseEntity<BookVO> update(BookVO book) {
        var bookVO = bookService.update(book);
        addHateoas(bookVO);
        return ResponseEntity.status(HttpStatus.OK).body(bookVO);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void addHateoas(BookVO bookVO) {
        bookVO.add(linkTo(methodOn(PersonController.class).findById(bookVO.getKey())).withSelfRel());
    }
}
