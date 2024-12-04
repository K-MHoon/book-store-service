package com.kmhoon.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var book = Book.builder()
                .isbn("1234567890")
                .title("Title")
                .author("Author")
                .price(9.90)
                .build();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnNotDefinedThenValidationFails() {
        var book = Book.builder()
                .isbn("")
                .title("Title")
                .author("Author")
                .price(9.90)
                .build();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(2);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("ISBN은 필수 값입니다.", "ISBN 검증에 실패했습니다.");
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var book = Book.builder()
                .isbn("a234567890")
                .title("Title")
                .author("Author")
                .price(9.90)
                .build();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("ISBN 검증에 실패했습니다.");
    }

    @Test
    void whenTitleIsNotDefinedThenValidationFails() {
        var book = Book.builder()
                .isbn("1234567890")
                .title("")
                .author("Author")
                .price(9.90)
                .build();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("제목은 필수 값입니다.");
    }

    @Test
    void whenAuthorIsNotDefinedThenValidationFails() {
        var book = Book.builder()
                .isbn("1234567890")
                .title("Title")
                .author("")
                .price(9.90)
                .build();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("책 저자는 필수 값입니다.");
    }

    @Test
    void whenPriceIsNotDefinedThenValidationFails() {
        var book = Book.builder()
                .isbn("1234567890")
                .title("Title")
                .author("Author")
                .price(null)
                .build();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("책 가격은 필수 값입니다.");
    }

    @Test
    void whenPriceDefinedButZeroThenValidationFails() {
        var book = Book.builder()
                .isbn("1234567890")
                .title("Title")
                .author("Author")
                .price(0.0)
                .build();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("책 가격은 0 보다 커야합니다.");
    }

    @Test
    void whenPriceDefinedButNegativeThenValidationFails() {
        var book = Book.builder()
                .isbn("1234567890")
                .title("Title")
                .author("Author")
                .price(-9.90)
                .build();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("책 가격은 0 보다 커야합니다.");
    }
}