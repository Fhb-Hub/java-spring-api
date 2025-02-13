package com.fhbhub.javaspringapi.repository;


import com.fhbhub.javaspringapi.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
