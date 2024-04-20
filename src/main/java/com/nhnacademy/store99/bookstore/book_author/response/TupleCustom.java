package com.nhnacademy.store99.bookstore.book_author.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TupleCustom<T, Q> {
    T object1;
    Q object2;
}
