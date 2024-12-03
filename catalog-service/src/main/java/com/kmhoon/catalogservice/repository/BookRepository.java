package com.kmhoon.catalogservice.repository;

import com.kmhoon.catalogservice.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
