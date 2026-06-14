package com.firstclub.membershipprogram.services;

import com.firstclub.membershipprogram.dtos.SubscriptionRequest;
import com.firstclub.membershipprogram.dtos.SubscriptionStatus;
import com.firstclub.membershipprogram.dtos.UserSubscriptionDto;
import com.firstclub.membershipprogram.entities.MembershipTierEntity;
import com.firstclub.membershipprogram.entities.TierPlanPricingEntity;
import com.firstclub.membershipprogram.entities.UserSubscriptionEntity;
import com.firstclub.membershipprogram.mappers.UserSubscriptionMapper;

import com.firstclub.membershipprogram.repositories.MembershipTierRepository;
import com.firstclub.membershipprogram.repositories.TierPlanPricingRepository;
import com.firstclub.membershipprogram.repositories.UserSubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    UserSubscriptionRepository subscriptionRepository;

    @Autowired
    TierPlanPricingRepository pricingRepository;

    @Autowired
    MembershipTierRepository tierRepository;

    @Autowired
    UserSubscriptionMapper subscriptionMapper;

    @Override
    public UserSubscriptionDto createSubscription(SubscriptionRequest request) {
        Optional<UserSubscriptionEntity> existing = subscriptionRepository.findActiveSubscription(request.getUserName(),
                LocalDateTime.now());
        if(existing.isPresent())
            throw new IllegalStateException("Subscription already exists.");

        TierPlanPricingEntity pricing = pricingRepository.findActivePricing(request.getTierId(), request.getPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid or inactive Tier/Plan combination selected."));

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusMonths(pricing.getPlan().getDurationMonths());

        UserSubscriptionEntity subscription = new UserSubscriptionEntity();
        subscription.setUserName(request.getUserName());
        subscription.setTierPlanPricing(pricing);
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);


        UserSubscriptionEntity savedEntity = subscriptionRepository.save(subscription);

        return subscriptionMapper.map(savedEntity);
    }

    @Override
    public UserSubscriptionDto changeTier(String userName, Long tierId) {
        UserSubscriptionEntity activeSub = subscriptionRepository.findActiveSubscriptionForUpdate(userName)
                .orElseThrow(() -> new IllegalStateException("No active subscription found to modify."));

        MembershipTierEntity currentTier = activeSub.getTierPlanPricing().getTier();
        MembershipTierEntity targetTier = tierRepository.findById(tierId)
                .orElseThrow(() -> new IllegalArgumentException("Target tier not found."));

        if (currentTier.getId().equals(targetTier.getId())) {
            throw new IllegalArgumentException("User is already on this tier.");
        }

        Long currentPlanId = activeSub.getTierPlanPricing().getPlan().getId();
        TierPlanPricingEntity newPricing = pricingRepository.findActivePricing(targetTier.getId(), currentPlanId)
                .orElseThrow(() -> new IllegalArgumentException("Pricing matrix configuration missing for target tier and current plan."));


        String actionType = targetTier.getTierLevel() > currentTier.getTierLevel() ? "UPGRADE" : "DOWNGRADE";

        activeSub.setStatus(SubscriptionStatus.valueOf(actionType + "D"));
        subscriptionRepository.save(activeSub);

        UserSubscriptionEntity newSubscription = new UserSubscriptionEntity();
        newSubscription.setUserName(userName);
        newSubscription.setTierPlanPricing(newPricing);
        newSubscription.setStatus(SubscriptionStatus.ACTIVE);
        newSubscription.setStartDate(LocalDateTime.now());
        newSubscription.setEndDate(activeSub.getEndDate());

        UserSubscriptionEntity savedEntity = subscriptionRepository.save(newSubscription);

        return subscriptionMapper.map(savedEntity);
    }

    @Override
    public void cancelSubscription(String userName) {

    }

    @Override
    public UserSubscriptionDto getCurrentMembership(String userName) {
        return null;
    }
}
