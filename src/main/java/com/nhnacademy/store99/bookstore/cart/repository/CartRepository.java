package com.nhnacademy.store99.bookstore.cart.repository;

import com.nhnacademy.store99.bookstore.cart.entity.Cart;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author seunggyu-kim
 */
public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom {
    Optional<Cart> findByUser_IdAndBook_Id(Long userId, Long bookId);

    List<Cart> findAllByUser_Id(Long userId);
}
