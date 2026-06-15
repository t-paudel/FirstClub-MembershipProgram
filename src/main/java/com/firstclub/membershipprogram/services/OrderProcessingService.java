package com.firstclub.membershipprogram.services;

import com.firstclub.membershipprogram.dtos.MembershipTierDto;
import com.firstclub.membershipprogram.dtos.UserSubscriptionDto;
import com.firstclub.membershipprogram.repositories.UserMonthlyMetricRepository;
import com.firstclub.membershipprogram.tierEvaluation.TierEvaluationEngine;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class OrderProcessingService {

    @Autowired
    private MembershipService membershipService;

    @Autowired
    UserMonthlyMetricRepository metricRepository;

    @Autowired
    TierEvaluationEngine evaluationEngine;

    @Autowired
    MembershipBenefitApplier benefitApplier;

    @Transactional
    public BigDecimal simulateCheckout(String userName, BigDecimal baseCartAmount) {
        BigDecimal finalPrice = benefitApplier.calculateDiscountedPrice(userName, baseCartAmount);

        boolean freeDelivery = benefitApplier.hasFreeDelivery(userName);

        String currentMonthYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
//        Optional<UserMonthlyMetricEntity> data = metricRepository.findByUserNameAndMonthYear(userName, currentMonthYear);
        metricRepository.incrementUserMetrics(userName, currentMonthYear, finalPrice);

        MembershipTierDto eligibleTier = evaluationEngine.evaluateEligibleTier(userName);

        UserSubscriptionDto userSubscriptionDto = membershipService.getCurrentMembership(userName);
        if(userSubscriptionDto != null) {
            int currentLevel = userSubscriptionDto.getTierPlanPricing().getTier().getTierLevel();
            if(eligibleTier.getTierLevel() > currentLevel)
                membershipService.changeTier(userName, eligibleTier.getId());
        }

        return finalPrice;
    }
}
