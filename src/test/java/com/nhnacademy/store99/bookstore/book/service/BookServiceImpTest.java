package com.nhnacademy.store99.bookstore.book.service;


import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookServiceImp.class)
class BookServiceImpTest {
    @Mock
    BookRepository bookRepository;
    private MockMvc mockMvc;

//    @Test
//    public void createBookTest() {
//
//
//        Book book = Book.builder()
//                .bookIsbn13(bookDTO.getBookIsbn13())
//                .bookIsbn11(bookDTO.getBookIsbn11())
//                .bookTitle(bookDTO.getBookTitle())
//                .bookContents(bookDTO.getBookContents())
//                .bookDescription(bookDTO.getBookDescription())
//                .bookPublisher(bookDTO.getBookPublisher())
//                .bookDate(bookDTO.getBookDateTime())
//                .bookPrice(bookDTO.getBookPrice())
//                .bookSalePrice(bookDTO.getBookSalePrice())
//                .bookThumbnailUrl(bookDTO.getBookThumbnailUrl())
//                .bookStock(bookDTO.getBookStock())
//                .build();
//
//        when(bookRepository.save(book)).thenReturn(new BookDTO(
//                book.getBookIsbn13(),
//                book.getBookIsbn11(),
//                book.getBookTitle(),
//                book.getBookContents(),
//
//                ));
//    }
}