package com.firstclub.membershipprogram.tierEvaluation;

import com.firstclub.membershipprogram.dtos.MembershipTierDto;
import com.firstclub.membershipprogram.entities.MembershipTierEntity;
import com.firstclub.membershipprogram.mappers.MembershipTierMapper;
import com.firstclub.membershipprogram.repositories.MembershipTierRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TierEvaluationEngine {

    private final List<TierEvaluationStrategy> strategies;
    private final MembershipTierRepository tierRepository;
    private final MembershipTierMapper tierMapper;

    public TierEvaluationEngine(List<TierEvaluationStrategy> strategies, MembershipTierRepository tierRepository,
                                MembershipTierMapper tierMapper) {
        this.strategies = strategies;
        this.tierRepository = tierRepository;
        this.tierMapper = tierMapper;
    }

    public MembershipTierDto evaluateEligibleTier(String userName) {
        List<MembershipTierEntity> allTiersDescending = tierRepository.findAllByOrderByTierLevelAsc().reversed();
        List<MembershipTierDto> dtoList = tierMapper.map(allTiersDescending);

        for (MembershipTierDto tier : dtoList) {
            for (TierEvaluationStrategy strategy : strategies) {
                if (strategy.qualifies(userName, tier)) {
                    return tier;
                }
            }
        }

        MembershipTierEntity data = tierRepository.findAllByOrderByTierLevelAsc().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No default membership tiers configured in database."));

        return tierMapper.map(data);
    }
}
