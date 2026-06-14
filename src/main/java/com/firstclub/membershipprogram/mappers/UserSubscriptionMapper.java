package com.firstclub.membershipprogram.mappers;

import com.firstclub.membershipprogram.dtos.*;
import com.firstclub.membershipprogram.entities.UserSubscriptionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSubscriptionMapper {

    public UserSubscriptionDto map(UserSubscriptionEntity entity) {
        UserSubscriptionDto userDto = new UserSubscriptionDto();
        userDto.setId(entity.getId());
        userDto.setUserName(entity.getUserName());
        userDto.setStatus(entity.getStatus());

        TierPlanPricingDto tierPlanDto = new TierPlanPricingDto();
        tierPlanDto.setId(entity.getTierPlanPricing().getId());
        tierPlanDto.setPrice(entity.getTierPlanPricing().getPrice());

        MembershipTierDto membershipTierDto = new MembershipTierDto();
        membershipTierDto.setId(entity.getTierPlanPricing().getTier().getId());
        membershipTierDto.setName(entity.getTierPlanPricing().getTier().getName());
        membershipTierDto.setTierLevel(entity.getTierPlanPricing().getTier().getTierLevel());

//        List<TierBenefitDto> tierBenefitDtoList = new ArrayList<>();
//        entity.getTierPlanPricing().getTier().getBenefits().forEach(benefit -> {
//            TierBenefitDto dto = new TierBenefitDto();
//            dto.setId(benefit.getId());
//            dto.setTierLevel(benefit.getTierLevel());
//            dto.setBenefitType(benefit.getBenefit().getBenefitType());
//            dto.setBenefitValue(benefit.getBenefitValue());
//
//            tierBenefitDtoList.add(dto);
//        });
//
//        membershipTierDto.setBenefits(tierBenefitDtoList);
        MembershipPlanDto membershipPlanDto = new MembershipPlanDto();
        membershipPlanDto.setId(entity.getTierPlanPricing().getPlan().getId());
        membershipPlanDto.setName(entity.getTierPlanPricing().getPlan().getName());
        membershipPlanDto.setDurationMonths(entity.getTierPlanPricing().getPlan().getDurationMonths());

        tierPlanDto.setTier(membershipTierDto);
        tierPlanDto.setPlan(membershipPlanDto);
        userDto.setTierPlanPricing(tierPlanDto);

        return userDto;
    }
}
