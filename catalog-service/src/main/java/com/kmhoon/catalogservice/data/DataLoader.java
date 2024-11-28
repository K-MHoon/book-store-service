package com.kmhoon.catalogservice.data;

import com.kmhoon.catalogservice.domain.Book;
import com.kmhoon.catalogservice.repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class DataLoader {

    private final BookRepository bookRepository;

    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        var book1 = new Book("1234567891", "시바의 모험", "김시바", 11.10);
        var book2 = new Book("1234567892", "걸리버 시바", "박시바", 13.10);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
