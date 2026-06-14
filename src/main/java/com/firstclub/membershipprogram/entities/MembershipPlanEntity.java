package com.firstclub.membershipprogram.entities;

import com.firstclub.membershipprogram.dtos.PlanCode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "membership_plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipPlanEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_code", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PlanCode planCode;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(nullable = false)
    private Double price;

    @Column(name = "duration_days", nullable = false)
    private Integer durationDays;

    @Column(nullable = false)
    private Boolean active;
}
