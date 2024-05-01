package com.nhnacademy.store99.bookstore.cart.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.store99.bookstore.auth.entity.Auth;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.bookstore.cart.entity.Cart;
import com.nhnacademy.store99.bookstore.cart.repository.CartRepository;
import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.grade.entity.Grade;
import com.nhnacademy.store99.bookstore.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * @author seunggyu-kim
 */
@DataJpaTest
class CartRepositoryImplTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CartRepository cartRepository;

    private User user;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        Grade grade = Grade.builder()
                .gradeName("BASIC")
                .gradeStartCost(0)
                .gradeEndCost(1000000)
                .gradeRatio(10)
                .build();
        entityManager.persist(grade);

        Auth auth = Auth.builder()
                .authName("USER")
                .build();
        entityManager.persist(auth);

        Consumer consumer = Consumer.builder()
                .consumerName("consumer name")
                .consumerEmail("user@naver.com")
                .consumerPhone("01012345678")
                .consumerPassword("$2a$12$S7/5PoUN5HtdFEGZoEByg.BF45kovwhZaiR5fKwXtrXmJ.QlfGd7S")
                .build();
        entityManager.persist(consumer);

        user = User.builder()
                .userBirthdate(LocalDate.parse("2007-12-03"))
                .grade(grade)
                .auth(auth)
                .consumers(consumer)
                .build();
        entityManager.persist(user);

        book1 = Book.builder()
                .bookIsbn13("1234567890123")
                .bookIsbn10("1234567890")
                .bookTitle("book title1")
                .bookContents("book contents1")
                .bookDescription("book description1")
                .bookPublisher("book publisher")
                .bookDate(LocalDateTime.parse("2007-12-03T10:15:30"))
                .bookPrice(10000)
                .bookSalePrice(9000)
                .bookIsPacked(false)
                .bookThumbnailUrl("https://via.placeholder.com/200x303")
                .build();
        entityManager.persist(book1);

        book2 = Book.builder()
                .bookIsbn13("9234567890123")
                .bookIsbn10("9234567890")
                .bookTitle("book title2")
                .bookContents("book contents2")
                .bookDescription("book description2")
                .bookPublisher("book publisher")
                .bookDate(LocalDateTime.parse("2007-12-03T10:15:30"))
                .bookPrice(20000)
                .bookSalePrice(15000)
                .bookIsPacked(false)
                .bookThumbnailUrl("https://via.placeholder.com/200x303")
                .build();
        entityManager.persist(book2);
    }

    @Test
    @DisplayName("사용자 ID로 장바구니 아이템 조회")
    void getCartItemsByUser() {
        // given
        Cart cart1 = Cart.builder()
                .cartAmount(1)
                .user(user)
                .book(book1)
                .build();
        entityManager.persist(cart1);

        Cart cart2 = Cart.builder()
                .cartAmount(2)
                .user(user)
                .book(book2)
                .build();
        entityManager.persist(cart2);

        // when
        List<CartItemResponse> cartItemResponses = cartRepository.getCartItemsByUser(user.getId());

        // then
        List<CartItemResponse> expectedCartItemResponses = new ArrayList<>();
        CartItemResponse expectedCartItemResponse1 = new CartItemResponse(
                book1.getId(),
                book1.getBookTitle(),
                book1.getBookPrice(),
                book1.getBookSalePrice(),
                book1.getBookThumbnailUrl(),
                book1.getBookStock(),
                cart1.getCartAmount()
        );
        expectedCartItemResponses.add(expectedCartItemResponse1);
        CartItemResponse expectedCartItemResponse2 = new CartItemResponse(
                book2.getId(),
                book2.getBookTitle(),
                book2.getBookPrice(),
                book2.getBookSalePrice(),
                book2.getBookThumbnailUrl(),
                book2.getBookStock(),
                cart2.getCartAmount()
        );
        expectedCartItemResponses.add(expectedCartItemResponse2);

        assertThat(cartItemResponses).usingRecursiveComparison().isEqualTo(expectedCartItemResponses);
    }

    @Test
    @DisplayName("사용자 ID로 장바구니 아이템 조회 - 장바구니가 비어있는 경우")
    public void getCartItemsByUserWhenEmptyCart() {
        // given
        // then
        List<CartItemResponse> cartItems = cartRepository.getCartItemsByUser(1L);

        // 그러면
        assertThat(cartItems).isEmpty();
    }

    @Test
    @DisplayName("사용자 ID로 장바구니 아이템 조회 - 사용자가 존재하지 않는 경우")
    public void getCartItemsByUserWhenUserNotFound() {
        // given
        // when
        List<CartItemResponse> cartItems = cartRepository.getCartItemsByUser(999L);

        // then
        assertThat(cartItems).isEmpty();
    }
}