package com.nhnacademy.store99.bookstore.order_book.repository;

import com.nhnacademy.store99.bookstore.order_book.DTO.response.BestBookResponse;
import com.nhnacademy.store99.bookstore.order_book.DTO.response.LatestBookResponse;
import java.util.List;

public interface OrderBookRepository {

    // 이 쿼리는 각 도서의 주문갯수를 가져옴. 그리고 가장 많이 팔리는걸 처음으로 가져옴.
    // select ob.book_id, COUNT(ob.book_id)  from order_book ob GROUP BY ob.book_id  Order By COUNT(ob.book_id) DESC;

    // 이게 가장 잘팔리는 도서들 가져옴.
    // select b.*  from order_book ob join books as b where ob.book_id = b.book_id GROUP BY ob.book_id Order By COUNT(ob.book_id) DESC ;
    List<BestBookResponse> bestBooks();

    // 이거는 최신 도서를 가져옴
    // select b.book_id, b.created_at  from books b ORDER BY b.created_at DESC, b.book_id DESC;
    List<LatestBookResponse> latestBooks();
}


