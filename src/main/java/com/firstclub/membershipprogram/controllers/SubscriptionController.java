package com.firstclub.membershipprogram.controllers;

import com.firstclub.membershipprogram.dtos.SubscriptionRequest;
import com.firstclub.membershipprogram.dtos.UserSubscriptionDto;
import com.firstclub.membershipprogram.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/apis/subscriptions")
public class SubscriptionController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping("/{userName}")
    public UserSubscriptionDto getCurrentMembership(@PathVariable String userName) {
        return membershipService.getCurrentMembership(userName);
    }

    @PutMapping
    public UserSubscriptionDto changeTier(@RequestBody SubscriptionRequest request) {
        return membershipService.changeTier(request.getUserName(), request.getTierId());
    }

    @PostMapping()
    public UserSubscriptionDto createSubscription(@RequestBody SubscriptionRequest request) {
        return membershipService.createSubscription(request);
    }

    @DeleteMapping("/{userName}")
    public void updateSubscription(@PathVariable String userName) {
        membershipService.cancelSubscription(userName);
    }
}
