package com.nhnacademy.store99.bookstore.order_book.entity.service;

import com.nhnacademy.store99.bookstore.order_book.entity.response.LatestBookResponse;
import java.util.List;
import java.util.Map;

public interface OrderBookService {

    // 이 쿼리는 각 도서의 주문갯수를 가져옴. 그리고 가장 많이 팔리는걸 처음으로 가져옴.
    // select ob.book_id, COUNT(ob.book_id)  from order_book ob GROUP BY ob.book_id  Order By COUNT(ob.book_id) DESC;
    Map<Long, Long> bestBooks();

    // 이거는 최신 도서를 가져옴
    // select b.book_id, b.created_at  from books b ORDER BY b.created_at DESC, b.book_id DESC;
    List<LatestBookResponse> latestBooks();
}
