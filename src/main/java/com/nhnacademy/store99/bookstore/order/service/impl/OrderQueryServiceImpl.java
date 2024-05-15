package com.nhnacademy.store99.bookstore.order.service.impl;

import com.nhnacademy.store99.bookstore.order.dto.request.OrderInquiryByGuestRequest;
import com.nhnacademy.store99.bookstore.order.dto.response.OrderInquiryResponse;
import com.nhnacademy.store99.bookstore.order.exception.OrderBadRequestException;
import com.nhnacademy.store99.bookstore.order.repository.OrderRepository;
import com.nhnacademy.store99.bookstore.order.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author seunggyu-kim
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryServiceImpl implements OrderQueryService {
    private final OrderRepository orderRepository;

    @Override
    public OrderInquiryResponse getOrderByGuest(final OrderInquiryByGuestRequest orderInquiryRequest) {
        OrderInquiryResponse orderByGuest = orderRepository.getOrderByGuest(orderInquiryRequest.getOrderId(),
                orderInquiryRequest.getOrderPassword());
        if (orderByGuest == null) {
            throw new OrderBadRequestException("주문 정보가 없습니다.");
        }
        return orderByGuest;
    }
}
