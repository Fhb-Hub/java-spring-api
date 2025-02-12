package com.fhbhub.javaspringapi.service;

import com.fhbhub.javaspringapi.converter.DozerConverter;
import com.fhbhub.javaspringapi.data.model.Book;
import com.fhbhub.javaspringapi.data.vo.BookVO;
import com.fhbhub.javaspringapi.exception.ResourceNotFoundException;
import com.fhbhub.javaspringapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookVO create(BookVO bookVO) {
        var entity = DozerConverter.parseObject(bookVO, Book.class);
        return DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
    }

    public List<BookVO> findAll() {
        return DozerConverter.parseListObjects(bookRepository.findAll(), BookVO.class);
    }

    public BookVO findById(Long id) {
        var book = getBookById(id);
        return DozerConverter.parseObject(book, BookVO.class);
    }

    public BookVO update(BookVO bookVO) {
        var book = getBookById(bookVO.getKey());
        book.setTitle(bookVO.getTitle());
        book.setAuthor(bookVO.getAuthor());
        book.setLaunchDate(bookVO.getLaunchDate());
        book.setPrice(bookVO.getPrice());

        return DozerConverter.parseObject(bookRepository.save(book), BookVO.class);
    }

    public void delete(Long id) { bookRepository.delete(getBookById(id)); }

    private Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }
}
