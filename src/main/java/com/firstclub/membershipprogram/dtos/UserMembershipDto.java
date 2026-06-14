package com.firstclub.membershipprogram.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMembershipDto {
    private String userName;
//    private PlanType plan;
//    private TierType tier;
    private SubscriptionStatus status;
    private Boolean autoRenew;
}
