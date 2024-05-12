package com.nhnacademy.store99.bookstore.book_image.repository;

import com.nhnacademy.store99.bookstore.book_image.dto.response.BookImageDTO;

public interface BookImageRepository {
    BookImageDTO getBookImageData(Long bookId);
}
