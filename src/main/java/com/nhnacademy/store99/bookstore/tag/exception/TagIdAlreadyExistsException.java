package com.nhnacademy.store99.bookstore.tag.exception;

import com.nhnacademy.store99.bookstore.common.exception.AlreadyExistsException;

public class TagIdAlreadyExistsException extends AlreadyExistsException {
    public TagIdAlreadyExistsException(Long id) {
        super(String.format("TagId %d already exists", id));
    }
}
