package com.nhnacademy.store99.bookstore.order.dto.response;


import com.nhnacademy.store99.bookstore.order.enums.OrderState;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
@AllArgsConstructor
public class OrderInquiryResponse {
    private String orderId;
    private OrderState orderState;
    private LocalDateTime orderReleaseAt;
    private Integer orderTotalCost;
    private Integer orderDeliveryCost;
    private Integer orderUsePoint;
    private Integer couponDiscount;
    private Integer orderFinalCost;

    private List<BookInOrderInquiryResponse> orderBookList;

    private String buyerName;
    private String buyerPhone;
    private String buyerEmail;

    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private Integer recipientPostcode;
    private String recipientDetailAddress;

    public String getOrderState() {
        return orderState.getValue();
    }
}
