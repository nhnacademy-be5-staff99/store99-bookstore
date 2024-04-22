package com.nhnacademy.store99.bookstore.book_author.response;

import com.nhnacademy.store99.bookstore.author.entity.Author;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookPageDTO {
    Book book;
    List<Author> authors;
}
