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
        var book = new Book("1234567890", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnNotDefinedThenValidationFails() {
        var book = new Book("", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(2);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("ISBN은 필수 값입니다.", "ISBN 검증에 실패했습니다.");
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var book = new Book("a234567890", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("ISBN 검증에 실패했습니다.");
    }

    @Test
    void whenTitleIsNotDefinedThenValidationFails() {
        var book = new Book("1234567890", "", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("제목은 필수 값입니다.");
    }

    @Test
    void whenAuthorIsNotDefinedThenValidationFails() {
        var book = new Book("1234567890", "Title", "", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("책 저자는 필수 값입니다.");
    }

    @Test
    void whenPriceIsNotDefinedThenValidationFails() {
        var book = new Book("1234567890", "Title", "Author", null);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("책 가격은 필수 값입니다.");
    }

    @Test
    void whenPriceDefinedButZeroThenValidationFails() {
        var book = new Book("1234567890", "Title", "Author", 0.0);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("책 가격은 0 보다 커야합니다.");
    }

    @Test
    void whenPriceDefinedButNegativeThenValidationFails() {
        var book = new Book("1234567890", "Title", "Author", -9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertThat(messages).contains("책 가격은 0 보다 커야합니다.");
    }
}