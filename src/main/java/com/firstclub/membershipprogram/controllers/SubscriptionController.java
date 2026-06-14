package com.firstclub.membershipprogram.controllers;

import com.firstclub.membershipprogram.dtos.SubscriptionRequest;
import com.firstclub.membershipprogram.dtos.UserSubscriptionDto;
import com.firstclub.membershipprogram.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/apis/memberships")
public class SubscriptionController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping()
    public UserSubscriptionDto createSubscription(@RequestBody SubscriptionRequest request) {
        return membershipService.createSubscription(request);
    }
}
