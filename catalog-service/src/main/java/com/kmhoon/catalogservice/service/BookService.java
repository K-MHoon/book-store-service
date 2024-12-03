package com.kmhoon.catalogservice.service;

import com.kmhoon.catalogservice.domain.Book;
import com.kmhoon.catalogservice.exception.BookAlreadyExistsException;
import com.kmhoon.catalogservice.exception.BookNotFoundException;
import com.kmhoon.catalogservice.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if(bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistsException(book.getIsbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    existingBook.updateAll(book.getTitle(), book.getAuthor(), book.getPrice(), book.getPublisher());
                    return bookRepository.save(existingBook);
                })
                .orElseGet(() -> addBookToCatalog(book));
    }
}
