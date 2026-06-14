package com.firstclub.membershipprogram.repositories;

import com.firstclub.membershipprogram.entities.TierPlanPricingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TierPlanPricingRepository extends JpaRepository<TierPlanPricingEntity,Long> {

    @Query("SELECT t FROM TierPlanPricingEntity t " +
            "JOIN FETCH t.tier " +
            "JOIN FETCH t.plan " +
            "WHERE t.tier.id = :tierId AND t.plan.id = :planId AND t.isActive = true")
    Optional<TierPlanPricingEntity> findActivePricing(@Param("tierId") Long tierId,
                                                      @Param("planId") Long planId);
}
