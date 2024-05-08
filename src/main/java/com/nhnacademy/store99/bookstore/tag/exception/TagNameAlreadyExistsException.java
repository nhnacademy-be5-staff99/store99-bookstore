package com.nhnacademy.store99.bookstore.tag.exception;

import com.nhnacademy.store99.bookstore.common.exception.AlreadyExistsException;

public class TagNameAlreadyExistsException extends AlreadyExistsException {
    public TagNameAlreadyExistsException(String tagName) {
        super(String.format("TagName '%s' already exists", tagName));
    }
}
