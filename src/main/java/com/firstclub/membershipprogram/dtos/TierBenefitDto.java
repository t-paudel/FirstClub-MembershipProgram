package com.firstclub.membershipprogram.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TierBenefitDto {
    private Long id;
    private String tierLevel;
    private BenefitType benefitType;
    private String benefitValue;
}
