package com.firstclub.membershipprogram.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "membership_tiers")
public class MembershipTierEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "tier_level", nullable = false, unique = true)
    private Integer tierLevel;

    @OneToMany(mappedBy = "membership_tier_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TierBenefitEntity> benefits;
}
