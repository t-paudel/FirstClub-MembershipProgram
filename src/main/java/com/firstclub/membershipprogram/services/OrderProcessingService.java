package com.firstclub.membershipprogram.services;

import com.firstclub.membershipprogram.dtos.*;
import com.firstclub.membershipprogram.repositories.UserMonthlyMetricRepository;
import com.firstclub.membershipprogram.tierEvaluation.TierEvaluationEngine;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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
    public CheckoutDto simulateCheckout(String userName, BigDecimal baseCartAmount) {
        CheckoutDto checkoutDto = new CheckoutDto();
        AmountDto amountDto = new AmountDto();

        BigDecimal productPrice = benefitApplier.calculateDiscountedPrice(userName, baseCartAmount);
        BigDecimal deliveryCharges = benefitApplier.calculateDeliveryCharges(userName);

        amountDto.setProductPrice(productPrice);
        amountDto.setDeliveryCharges(deliveryCharges);
        amountDto.setTotalPrice(productPrice.add(deliveryCharges));

        String currentMonthYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        metricRepository.incrementUserMetrics(userName, currentMonthYear, productPrice);

        MembershipTierDto eligibleTier = evaluationEngine.evaluateEligibleTier(userName);

        UserSubscriptionDto userSubscriptionDto = membershipService.getCurrentMembership(userName);
        if(userSubscriptionDto != null) {
            Map<BenefitType, String> benefitMap = new HashMap<>();
            int currentLevel = userSubscriptionDto.getTierPlanPricing().getTier().getTierLevel();
            if(eligibleTier.getTierLevel() > currentLevel) {
                membershipService.changeTier(userName, eligibleTier.getId());

                eligibleTier.getBenefits()
                        .forEach(b -> benefitMap.put(b.getBenefitType(), b.getBenefitValue()));
                checkoutDto.setTierType(eligibleTier.getName());
            } else {
                userSubscriptionDto.getTierPlanPricing().getTier().getBenefits()
                        .forEach(b -> benefitMap.put(b.getBenefitType(), b.getBenefitValue()));
                checkoutDto.setTierType(userSubscriptionDto.getTierPlanPricing().getTier().getName());
            }
            checkoutDto.setBenefits(benefitMap);
        }

        checkoutDto.setAmount(amountDto);

        return checkoutDto;
    }
}
