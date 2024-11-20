package com.kmhoon.catalogservice.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn) {
        super("ISBN [" + isbn + "] 을 찾을 수 없습니다.");
    }
}
