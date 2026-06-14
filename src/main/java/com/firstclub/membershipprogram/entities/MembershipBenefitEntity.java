package com.firstclub.membershipprogram.entities;

import com.firstclub.membershipprogram.dtos.BenefitType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "membership_benefit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipBenefitEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "benefit_code", nullable = false, unique = true)
    private String benefitCode;

    @Column(name = "benefit_name", nullable = false)
    private String benefitName;

    @Enumerated(EnumType.STRING)
    @Column(name = "benefit_type", nullable = false)
    private BenefitType benefitType;

    @Column(name = "benefit_value")
    private Double benefitValue;

    @Column(nullable = false)
    private Boolean active;
}
