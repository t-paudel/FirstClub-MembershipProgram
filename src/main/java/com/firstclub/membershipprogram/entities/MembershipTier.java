package com.firstclub.membershipprogram.entities;

import com.firstclub.membershipprogram.dtos.TierCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "membership_tier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipTier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tier_code", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private TierCode tierCode;

    @Column(name = "tier_name", nullable = false)
    private String tierName;

    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false)
    private Boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tier_benefit_mapping",
            joinColumns = @JoinColumn(name = "tier_id"),
            inverseJoinColumns = @JoinColumn(name = "benefit_id")
    )
    private Set<MembershipBenefit> benefits;
}
