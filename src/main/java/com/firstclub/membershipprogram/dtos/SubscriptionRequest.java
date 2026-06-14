package com.firstclub.membershipprogram.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubscriptionRequest {
    private String userName;
    private Long planId;
    private Long tierId;
}
