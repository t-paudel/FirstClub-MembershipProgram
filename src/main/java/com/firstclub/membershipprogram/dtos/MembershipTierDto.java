package com.firstclub.membershipprogram.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MembershipTierDto {
    private Long id;
    private String name;
    private int tierLevel;
    private List<TierBenefitDto> benefits;
}
