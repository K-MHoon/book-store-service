package com.kmhoon.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(
        @NotBlank(message = "ISBN은 필수 값입니다.")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "ISBN 검증에 실패했습니다.")
        String isbn,

        @NotBlank(message = "제목은 필수 값입니다.")
        String title,

        @NotBlank(message = "책 저자는 필수 값입니다.")
        String author,

        @NotNull(message = "책 가격은 필수 값입니다.")
        @Positive(message = "책 가격은 0 보다 커야합니다.")
        Double price

        ) {
}
