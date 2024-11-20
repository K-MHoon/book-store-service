package com.kmhoon.catalogservice.exception;

public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String isbn) {
        super("ISBN [" + isbn + "] 은 이미 존재합니다.");
    }
}
