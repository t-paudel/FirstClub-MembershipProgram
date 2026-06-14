package com.firstclub.membershipprogram.controllers;

import com.firstclub.membershipprogram.dtos.SubscriptionRequest;
import com.firstclub.membershipprogram.dtos.UserMembershipDto;
import com.firstclub.membershipprogram.entities.UserSubscriptionEntity;
import com.firstclub.membershipprogram.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/apis/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping("/plans")
    public List<UserMembershipDto> getPlans() {
        return membershipService.getPlans();
    }

    @PostMapping()
    public UserSubscriptionEntity createSubscription(@RequestBody SubscriptionRequest request) {
        return membershipService.createSubscription(request);
    }
}
