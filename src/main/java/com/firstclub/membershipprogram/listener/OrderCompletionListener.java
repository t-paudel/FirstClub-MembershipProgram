package com.firstclub.membershipprogram.listener;

import com.firstclub.membershipprogram.dtos.MembershipTierDto;
import com.firstclub.membershipprogram.dtos.UserSubscriptionDto;
import com.firstclub.membershipprogram.repositories.UserMonthlyMetricRepository;
import com.firstclub.membershipprogram.services.MembershipService;
import com.firstclub.membershipprogram.tierEvaluation.TierEvaluationEngine;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderCompletionListener {

    @Autowired
    private UserMonthlyMetricRepository metricRepository;

    @Autowired
    private TierEvaluationEngine evaluationEngine;

    @Autowired
    private MembershipService membershipService;

    @Transactional
    public void onOrderComplete(String userName, BigDecimal orderValue, String currentMonthYear) {
        metricRepository.incrementUserMetrics(userName, currentMonthYear, orderValue);

        MembershipTierDto eligibleTier = evaluationEngine.evaluateEligibleTier(userName);

        UserSubscriptionDto currentData = membershipService.getCurrentMembership(userName);
        if(currentData != null) {
            int currentLevel = currentData.getTierPlanPricing().getTier().getTierLevel();
            if(eligibleTier.getTierLevel() > currentLevel)
                membershipService.changeTier(userName, (long)eligibleTier.getTierLevel());
        }
    }
}
