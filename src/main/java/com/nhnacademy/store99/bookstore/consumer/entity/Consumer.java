package com.nhnacademy.store99.bookstore.consumer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Consumer Entity
 *
 * @author seunggyu-kim
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "consumers", schema = "staff99_bookstore")
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id", nullable = false)
    private Long id;

    @Column(name = "consumer_name", nullable = false, length = 50)
    private String consumerName;

    @Column(name = "consumer_email", nullable = false, length = 320, unique = true)
    private String consumerEmail;

    @Column(name = "consumer_phone", nullable = false, length = 11)
    private String consumerPhone;

    @Column(name = "consumer_password", nullable = false, length = 64)
    private String consumerPassword;

}