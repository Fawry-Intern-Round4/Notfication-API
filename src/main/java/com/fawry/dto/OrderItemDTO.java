package com.fawry.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private Long productId;
    private BigDecimal price;
    private int quantity;

    @Override
    public String toString() {
        return "\nitem id: "
                + this.productId
                + "\nitem price: "
                + this.price
                + "\nitem quantity: "
                + this.quantity
                + "\n";
    }
}
