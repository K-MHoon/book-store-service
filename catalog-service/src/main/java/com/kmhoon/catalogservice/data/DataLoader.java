package com.kmhoon.catalogservice.data;

import com.kmhoon.catalogservice.domain.Book;
import com.kmhoon.catalogservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
@RequiredArgsConstructor
public class DataLoader {

    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234567891", "시바의 모험", "김시바", 11.10, "김퍼블");
        var book2 = Book.of("1234567892", "걸리버 시바", "박시바", 13.10, "박퍼블");
        bookRepository.saveAll(List.of(book1,book2));
    }
}
