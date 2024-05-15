package com.nhnacademy.store99.bookstore.order.dto;

import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class ConsumerInOrderDto {
    private String consumerName;

    private String consumerEmail;

    private String consumerPhone;

    private String consumerPassword;

    public Consumer toEntity() {
        return Consumer.builder()
                .consumerName(consumerName)
                .consumerEmail(consumerEmail)
                .consumerPhone(consumerPhone)
                .consumerPassword(consumerPassword)
                .build();
    }
}
