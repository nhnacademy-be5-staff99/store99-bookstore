package com.nhnacademy.store99.bookstore.book_image.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookImageDTO {
    private Long FileId;
    private String BookImageURL;
    private String BookImageName;
}
