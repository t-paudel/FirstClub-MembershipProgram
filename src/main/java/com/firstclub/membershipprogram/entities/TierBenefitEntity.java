package com.firstclub.membershipprogram.entities;

import com.firstclub.membershipprogram.dtos.BenefitType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tier_benefits")
public class TierBenefitEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_tier_id", nullable = false)
    private MembershipTierEntity membershipTier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "benefit_id")
    private BenefitEntity benefit;

    @Column(name = "benefit_value", nullable = false)
    private String benefitValue;
}