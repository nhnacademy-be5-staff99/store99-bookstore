package com.nhnacademy.store99.bookstore.book_tag.service;

import com.nhnacademy.store99.bookstore.book.response.BookResponse;
import java.util.List;

public interface BookTagService {

    List<BookResponse.TagDTO> getTagByBookId(Long bookId);
}
