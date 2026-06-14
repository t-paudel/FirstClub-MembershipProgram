package com.firstclub.membershipprogram.services;

import com.firstclub.membershipprogram.dtos.SubscriptionRequest;
import com.firstclub.membershipprogram.dtos.UserMembershipDto;
import com.firstclub.membershipprogram.entities.UserSubscriptionEntity;

import java.util.List;

public interface MembershipService {
    List<UserMembershipDto> getPlans();

    void subscribe(UserMembershipDto userMembershipDto);

    UserSubscriptionEntity createSubscription(SubscriptionRequest request);

    UserSubscriptionEntity changeTier(String userName, Long tierId);

    void cancelSubscription(String userName);

    UserSubscriptionEntity getCurrentMembership(String userName);
}
