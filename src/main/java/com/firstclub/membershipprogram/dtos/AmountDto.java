package com.firstclub.membershipprogram.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class AmountDto {
    private BigDecimal productPrice;
    private BigDecimal deliveryCharges;
    private BigDecimal totalPrice;
}
