package com.firstclub.membershipprogram.entities;

import com.firstclub.membershipprogram.dtos.RuleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tier_rule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TierRuleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tier_id", nullable = false)
    private MembershipTierEntity tier;

    @Enumerated(EnumType.STRING)
    @Column(name = "rule_type", nullable = false)
    private RuleType ruleType;

    @Column(nullable = false)
    private String operator;

    @Column(name = "threshold_value", nullable = false)
    private String thresholdValue;

    @Column(nullable = false)
    private Boolean active;
}
