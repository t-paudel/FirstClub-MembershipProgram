package com.firstclub.membershipprogram.services;

import com.firstclub.membershipprogram.dtos.BenefitType;
import com.firstclub.membershipprogram.entities.TierBenefitEntity;
import com.firstclub.membershipprogram.entities.UserSubscriptionEntity;
import com.firstclub.membershipprogram.repositories.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipBenefitApplier {

    @Autowired
    private UserSubscriptionRepository subscriptionRepository;

    public BigDecimal calculateDiscountedPrice(String userName, BigDecimal originalPrice) {
        Optional<UserSubscriptionEntity> activeSubOpt = subscriptionRepository.findActiveSubscription(userName, LocalDateTime.now());

        if (activeSubOpt.isEmpty()) {
            return originalPrice;
        }

        UserSubscriptionEntity subscription = activeSubOpt.get();
        List<TierBenefitEntity> benefits = subscription.getTierPlanPricing().getTier().getBenefits();

        Optional<TierBenefitEntity> discountBenefit = benefits.stream()
                .filter(b -> BenefitType.CATEGORY_DISCOUNT.equals(b.getBenefit().getBenefitType()))
                .findFirst();

        if (discountBenefit.isPresent()) {
            double discountPercent = Double.parseDouble(discountBenefit.get().getBenefitValue());
            BigDecimal discountMultiplier = BigDecimal.valueOf((100 - discountPercent) / 100.0);
            return originalPrice.multiply(discountMultiplier);
        }

        return originalPrice;
    }

    public BigDecimal calculateDeliveryCharges(String userName) {
        return subscriptionRepository.findActiveSubscription(userName, LocalDateTime.now())
                .map(sub -> sub.getTierPlanPricing().getTier().getBenefits().stream()
                        .filter(b -> BenefitType.DELIVERY_CHARGES.equals(b.getBenefit().getBenefitType()))
                        .findFirst()
                        .map(b -> new BigDecimal(b.getBenefitValue()))
                        .orElse(new BigDecimal(150)))
                .orElse(new BigDecimal(150));
    }
}
