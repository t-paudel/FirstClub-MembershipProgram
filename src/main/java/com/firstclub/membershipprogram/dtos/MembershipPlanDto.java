package com.firstclub.membershipprogram.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MembershipPlanDto {
    private Long id;
    private String name;
    private int durationMonths;
}
