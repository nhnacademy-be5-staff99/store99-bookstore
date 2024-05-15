package com.nhnacademy.store99.bookstore.order.service.impl;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.consumer.repository.ConsumerRepository;
import com.nhnacademy.store99.bookstore.order.dto.request.ConfirmPaymentRequest;
import com.nhnacademy.store99.bookstore.order.entity.Order;
import com.nhnacademy.store99.bookstore.order.enums.OrderState;
import com.nhnacademy.store99.bookstore.order.exception.OrderBadRequestException;
import com.nhnacademy.store99.bookstore.order.repository.OrderRepository;
import com.nhnacademy.store99.bookstore.order.service.OrderByGuestService;
import com.nhnacademy.store99.bookstore.order_book.entity.OrderBook;
import com.nhnacademy.store99.bookstore.order_book.repository.OrderBookRepository;
import com.nhnacademy.store99.bookstore.payment.entity.Payment;
import com.nhnacademy.store99.bookstore.payment.repository.PaymentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author seunggyu-kim
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderByGuestByGuestServiceImpl implements OrderByGuestService {
    private final OrderRepository orderRepository;
    private final ConsumerRepository consumerRepository;
    private final BookRepository bookRepository;
    private final OrderBookRepository orderBookRepository;
    private final PaymentRepository paymentRepository;

    /**
     * 결제 버튼을 눌렀을 때, 구매자, 주문, 주문-도서 테이블 생성
     * 주문 상태는 PAYMENT_PENDING(결제 대기)로 생성
     *
     * @param confirmPaymentRequest 결제 요청 정보
     */
    @Override
    @Transactional
    public void confirmPayment(final ConfirmPaymentRequest confirmPaymentRequest) {
        Consumer consumer = confirmPaymentRequest.getConsumer().toEntity();
        consumerRepository.save(consumer);

        Order order = confirmPaymentRequest.getOrder().toEntity(consumer);
        orderRepository.save(order);

        List<OrderBook> orderBookList = confirmPaymentRequest.getOrderBooks().stream().map(orderBook -> {
            Book book = bookRepository.findByIdAndDeletedAtNull(orderBook.getBookId())
                    .orElseThrow(() -> new OrderBadRequestException("bookId is not found"));
            return orderBook.toEntity(book, order);
        }).collect(Collectors.toList());
        orderBookRepository.saveAll(orderBookList);
    }

    /**
     * 결제 취소 시, DB에서 주문 데이터 삭제
     */
    @Override
    @Transactional
    public void undoPendingPayment(final String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderBadRequestException("orderId is not found"));
        Consumer consumer = order.getConsumer();

        if (order.getOrderState() != OrderState.PAYMENT_PENDING) {
            throw new OrderBadRequestException("order is already completed");
        }

        if (order.getPayment() != null) {
            paymentRepository.delete(order.getPayment());
        }
        orderBookRepository.deleteAllByOrder(order);
        orderRepository.delete(order);
        consumerRepository.delete(consumer);
    }

    /**
     * 결제 성공 시, 주문 상태를 PAYMENT_COMPLETED(결제 완료)로 변경
     *
     * @param orderId
     * @param paymentKey 토스 페이먼츠 키
     */
    @Override
    @Transactional
    public void successPendingPayment(final String orderId, final String paymentKey) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderBadRequestException("order is not found"));

        if (order.getOrderState() != OrderState.PAYMENT_PENDING) {
            throw new OrderBadRequestException("order is already completed");
        }

        order.setOrderState(OrderState.PAYMENT_COMPLETED);
        orderRepository.save(order);

        Payment payment = Payment.builder()
                .paymentKey(paymentKey)
                .order(order)
                .build();
        paymentRepository.save(payment);
    }

    /**
     * 결제 실패 시, 주문 상태를 PAYMENT_FAILED(결제 실패)로 변경
     *
     * @param orderId
     * @param paymentKey 토스 페이먼츠 키
     */
    @Override
    @Transactional
    public void failPendingPayment(final String orderId, final String paymentKey) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderBadRequestException("order is not found"));

        if (order.getOrderState() != OrderState.PAYMENT_PENDING) {
            throw new OrderBadRequestException("order is already completed");
        }

        order.setOrderState(OrderState.PAYMENT_FAILED);
        orderRepository.save(order);

        Payment payment = Payment.builder()
                .paymentKey(paymentKey)
                .order(order)
                .build();
        paymentRepository.save(payment);
    }
}