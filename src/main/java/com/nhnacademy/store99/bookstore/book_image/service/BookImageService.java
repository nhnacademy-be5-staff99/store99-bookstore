package com.nhnacademy.store99.bookstore.book_image.service;

import com.nhnacademy.store99.bookstore.book_image.dto.response.BookImageDTO;

public interface BookImageService {
    BookImageDTO getBookImageData(Long bookId);
}
