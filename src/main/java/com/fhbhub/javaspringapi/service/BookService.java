package com.fhbhub.javaspringapi.service;

import com.fhbhub.javaspringapi.data.dto.BookDTO;
import com.fhbhub.javaspringapi.data.model.Book;
import com.fhbhub.javaspringapi.exception.ResourceNotFoundException;
import com.fhbhub.javaspringapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fhbhub.javaspringapi.mapper.ObjectMapper.parseListObjects;
import static com.fhbhub.javaspringapi.mapper.ObjectMapper.parseObject;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookDTO create(BookDTO bookDTO) {
        var entity = parseObject(bookDTO, Book.class);
        return parseObject(bookRepository.save(entity), BookDTO.class);
    }

    public List<BookDTO> findAll() {
        return parseListObjects(bookRepository.findAll(), BookDTO.class);
    }

    public BookDTO findById(Long id) {
        var book = getBookById(id);
        return parseObject(book, BookDTO.class);
    }

    public BookDTO update(BookDTO bookDTO) {
        var book = getBookById(bookDTO.getKey());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setLaunchDate(bookDTO.getLaunchDate());
        book.setPrice(bookDTO.getPrice());

        return parseObject(bookRepository.save(book), BookDTO.class);
    }

    public void delete(Long id) { bookRepository.delete(getBookById(id)); }

    private Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }
}
