package com.firstclub.membershipprogram.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class CheckoutDto {
    private String message;
    private AmountDto amount;
    private String tierType;
    private Map<BenefitType, String> benefits;
}
