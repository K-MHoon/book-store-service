package com.kmhoon.catalogservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Book {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "ISBN은 필수 값입니다.")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "ISBN 검증에 실패했습니다.")
        private String isbn;

        @NotBlank(message = "제목은 필수 값입니다.")
        private String title;

        @NotBlank(message = "책 저자는 필수 값입니다.")
        private String author;

        @NotNull(message = "책 가격은 필수 값입니다.")
        @Positive(message = "책 가격은 0 보다 커야합니다.")
        private Double price;

        private String publisher;

        @CreatedDate
        private Instant createdDate;

        @LastModifiedDate
        private Instant lastModifiedDate;

        @Version
        private int version;

        public static Book of(String isbn, String title, String author, Double price, String publisher) {
                return new Book(null, isbn, title, author, price, publisher, null, null, 0);
        }

        @Override
        public final boolean equals(Object o) {
                if (this == o) return true;
                if (o == null) return false;
                Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
                Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
                if (thisEffectiveClass != oEffectiveClass) return false;
                Book book = (Book) o;
                return getId() != null && Objects.equals(getId(), book.getId());
        }

        @Override
        public final int hashCode() {
                return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
        }

        public void updateAll(String title, String author, Double price, String publisher) {
                this.title = title;
                this.author = author;
                this.price = price;
                this.publisher = publisher;
        }

}
