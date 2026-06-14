package com.firstclub.membershipprogram.mappers;

import com.firstclub.membershipprogram.dtos.UserSubscriptionDto;
import com.firstclub.membershipprogram.entities.UserSubscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class UserSubscriptionMapper {

    public UserSubscriptionDto map(UserSubscriptionEntity entity) {
        UserSubscriptionDto dto = new UserSubscriptionDto();

        dto.setId(entity.getId());
        dto.setUserName(entity.getUserName());
        dto.setPlanName(entity.getTierPlanPricing().getPlan().getName());
        dto.setTierName(entity.getTierPlanPricing().getTier().getName());
        dto.setPrice(Double.valueOf(String.valueOf(entity.getTierPlanPricing().getPrice())));

        return dto;
    }
}
