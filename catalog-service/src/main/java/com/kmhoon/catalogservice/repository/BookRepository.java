package com.kmhoon.catalogservice.repository;

import com.kmhoon.catalogservice.domain.Book;

import java.util.Optional;

public interface BookRepository {

    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);

}
