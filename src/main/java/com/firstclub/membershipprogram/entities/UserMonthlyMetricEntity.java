package com.firstclub.membershipprogram.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_monthly_metrics",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_user_month", columnNames = {"user_name", "month_year"})
        })
public class UserMonthlyMetricEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "month_year", nullable = false, length = 7)
    private String monthYear;

    @Column(name = "order_count", nullable = false)
    private Integer orderCount = 0;

    @Column(name = "total_order_value", nullable = false)
    private BigDecimal totalOrderValue = BigDecimal.ZERO;
}
