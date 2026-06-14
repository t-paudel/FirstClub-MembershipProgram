package com.firstclub.membershipprogram.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TierPlanPricingDto {
    private Long id;
    private MembershipTierDto tier;
    private MembershipPlanDto plan;
    private BigDecimal price;
}
