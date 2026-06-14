package com.firstclub.membershipprogram.repositories;

import com.firstclub.membershipprogram.entities.UserSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscriptionEntity,Long> {

    @Query("SELECT u FROM UserSubscriptionEntity u " +
            "JOIN FETCH u.tierPlanPricing t " +
            "JOIN FETCH t.tier " +
            "WHERE u.userName = :userName AND u.status = 'ACTIVE' AND u.endDate > :now")
    Optional<UserSubscriptionEntity> findActiveSubscription(@Param("userName") String userName,
                                                            @Param("now") LocalDateTime now);

    @Query("SELECT u FROM UserSubscriptionEntity u WHERE u.userName = :userName AND u.status = 'ACTIVE'")
    Optional<UserSubscriptionEntity> findActiveSubscriptionForUpdate(@Param("userName") String userName);
}
