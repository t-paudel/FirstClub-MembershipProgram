package com.firstclub.membershipprogram.services;

import com.firstclub.membershipprogram.dtos.MembershipPlanDto;

import java.util.List;

public interface MembershipService {
    List<MembershipPlanDto> getPlans();
}
