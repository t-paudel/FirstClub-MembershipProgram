package com.firstclub.membershipprogram.services;

import com.firstclub.membershipprogram.dtos.SubscriptionRequest;
import com.firstclub.membershipprogram.dtos.UserSubscriptionDto;

public interface MembershipService {
    UserSubscriptionDto createSubscription(SubscriptionRequest request);

    UserSubscriptionDto changeTier(String userName, Long tierId);

    UserSubscriptionDto upgradeSubscription(SubscriptionRequest request);

    void cancelSubscription(String userName);

    UserSubscriptionDto getCurrentMembership(String userName);
}
