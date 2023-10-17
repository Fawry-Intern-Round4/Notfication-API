package com.fawry.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String guestEmail;
    private String couponCode;
    private Long couponID;
    private BigDecimal amount;
    private Date createdAt;
    private List<OrderItemDTO> orderItems;

    @Override
    public String toString() {
        return "your order have been placed successfully"
                +"\nyour order id: "
                +this.id
                +"\nyour order price: "
                +this.amount
                +"\nyour order created at: "
                +this.createdAt
                +(this.couponCode == null ? "" : "\nyour coupon code: " + this.couponCode)
                +"\nyour order items: "
                +this.orderItems;
    }
}
