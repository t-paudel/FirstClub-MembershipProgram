package com.firstclub.membershipprogram.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MembershipPlanDto {
    private Long id;
    private PlanCode planCode;
    private String planName;
    private Double price;
    private Integer durationDays;
    private Boolean active;
}
