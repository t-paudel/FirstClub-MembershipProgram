package com.firstclub.membershipprogram.controllers;

import com.firstclub.membershipprogram.dtos.SubscriptionRequest;
import com.firstclub.membershipprogram.dtos.UserSubscriptionDto;
import com.firstclub.membershipprogram.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/apis/subscriptions")
public class SubscriptionController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping("/{userName}")
    public ResponseEntity<UserSubscriptionDto> getCurrentMembership(@PathVariable String userName) {
        UserSubscriptionDto data = membershipService.getCurrentMembership(userName);
        return ResponseEntity.ok(data);
    }

    @PutMapping
    public ResponseEntity<UserSubscriptionDto> upgradeSubscription(@RequestBody SubscriptionRequest request) {
        UserSubscriptionDto data = membershipService.upgradeSubscription(request);
        return ResponseEntity.ok(data);
    }

    @PostMapping()
    public ResponseEntity<UserSubscriptionDto> createSubscription(@RequestBody SubscriptionRequest request) {
        UserSubscriptionDto data = membershipService.createSubscription(request);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<?> cancelSubscription(@PathVariable String userName) {
        membershipService.cancelSubscription(userName);
        return ResponseEntity.ok(Map.of(
                "Message", "Subscription cancelled successfully"
        ));
    }
}
