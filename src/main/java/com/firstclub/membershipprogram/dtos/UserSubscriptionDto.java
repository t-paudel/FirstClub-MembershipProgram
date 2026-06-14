package com.firstclub.membershipprogram.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSubscriptionDto {
    private Long id;
    private String userName;
    private String tierName;
    private String planName;
    private Double price;
}
