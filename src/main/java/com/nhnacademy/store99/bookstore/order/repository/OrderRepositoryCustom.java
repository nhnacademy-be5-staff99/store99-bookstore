package com.nhnacademy.store99.bookstore.order.repository;

import com.nhnacademy.store99.bookstore.order.dto.response.OrderInquiryResponse;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author seunggyu-kim
 */
@NoRepositoryBean
public interface OrderRepositoryCustom {
    OrderInquiryResponse getOrderByGuest(String orderId, String orderPassword);
}
