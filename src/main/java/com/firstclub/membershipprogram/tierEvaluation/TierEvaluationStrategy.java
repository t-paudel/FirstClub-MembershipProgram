package com.firstclub.membershipprogram.tierEvaluation;

import com.firstclub.membershipprogram.dtos.MembershipTierDto;

public interface TierEvaluationStrategy {
    boolean qualifies(String userName, MembershipTierDto membershipTier);
}
