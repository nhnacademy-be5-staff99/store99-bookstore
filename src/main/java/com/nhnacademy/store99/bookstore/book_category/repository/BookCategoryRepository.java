package com.nhnacademy.store99.bookstore.book_category.repository;

import com.nhnacademy.store99.bookstore.book.response.BookTransDTO;
import com.nhnacademy.store99.bookstore.book_category.response.BookCategoryResponse;
import com.nhnacademy.store99.bookstore.book_category.response.CategoryParentsDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookCategoryRepository {
    // 카테고리 하나로 자식 카테고리를 찾고 그걸로 도서까지 찾는건 코드가 너무 길어져서 나눔.

    // 카테고리 자식을 찾는 메소드
    List<CategoryParentsDTO> getCategoriesByParentsId(Long categoryId);


    // 카테고리들에 연결된 도서들를 가져오는 메소드
    // 도서정보를 가져와야하나,, 도서 id들만 가져와서 도서를 가져오는건 다른녀석을 사용? 해야할듯
    //  bookRepository.getBookDataById(bookId);
    Page<BookTransDTO> getBooksByCategories(List<CategoryParentsDTO> parentsDTOList, Pageable pageable);

    // 도서 한권의 카테고리를 가져오는 간단한 메소드
    BookCategoryResponse getCategoryByBookId(Long bookId);
}
