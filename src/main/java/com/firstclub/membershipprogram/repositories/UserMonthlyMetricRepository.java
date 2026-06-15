package com.firstclub.membershipprogram.repositories;

import com.firstclub.membershipprogram.entities.UserMonthlyMetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserMonthlyMetricRepository extends JpaRepository<UserMonthlyMetricEntity, String> {

    Optional<UserMonthlyMetricEntity> findByUserNameAndMonthYear(String userName, String monthYear);

    @Modifying
    @Query("UPDATE UserMonthlyMetricEntity u " +
            "SET u.orderCount = u.orderCount + 1, " +
            "u.totalOrderValue = u.totalOrderValue + :orderValue " +
            "WHERE u.userName = :userName AND u.monthYear = :monthYear")
    int incrementUserMetrics1(@Param("userName") String userName,
                             @Param("monthYear") String monthYear,
                             @Param("orderValue") BigDecimal orderValue);

    @Modifying
    @Query(value = """
    INSERT INTO user_monthly_metrics (user_name, month_year, order_count, total_order_value)
    VALUES (:userName, :monthYear, 1, :orderValue)
    ON DUPLICATE KEY UPDATE 
        order_count = order_count + 1,
        total_order_value = total_order_value + :orderValue
    """, nativeQuery = true)
    int incrementUserMetrics(@Param("userName") String userName,
                              @Param("monthYear") String monthYear,
                              @Param("orderValue") BigDecimal orderValue);
}
