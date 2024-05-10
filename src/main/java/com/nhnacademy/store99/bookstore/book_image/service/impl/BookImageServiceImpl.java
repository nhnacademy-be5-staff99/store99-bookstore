package com.nhnacademy.store99.bookstore.book_image.service.impl;

import com.nhnacademy.store99.bookstore.book_image.repository.impl.BookImageRepositoryImpl;
import com.nhnacademy.store99.bookstore.book_image.dto.response.BookImageDTO;
import com.nhnacademy.store99.bookstore.book_image.service.BookImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookImageServiceImpl implements BookImageService {

    @Qualifier("bookImageRepositoryImpl")
    private final BookImageRepositoryImpl bookImageRepository;


    @Override
    public BookImageDTO getBookImageData(Long bookId) {
        return bookImageRepository.getBookImageData(bookId);
    }
}
