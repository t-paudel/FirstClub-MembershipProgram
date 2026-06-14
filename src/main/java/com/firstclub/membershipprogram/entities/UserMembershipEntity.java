package com.firstclub.membershipprogram.entities;

import com.firstclub.membershipprogram.dtos.SubscriptionStatus;
//import com.firstclub.membershipprogram.dtos.PlanType;
//import com.firstclub.membershipprogram.dtos.TierType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "user_membership",
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id"),
                @Index(name = "idx_expiry_date", columnList = "expiry_date")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMembershipEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userName;

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private PlanType plan;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private TierType tier;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "auto_renew", nullable = false)
    private Boolean autoRenew;

    @Version
    private Long version;
}
