package com.firstclub.membershipprogram.controllers;

import com.firstclub.membershipprogram.dtos.UserSubscriptionDto;
import com.firstclub.membershipprogram.services.MembershipService;
import com.firstclub.membershipprogram.services.OrderProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/v1/apis/demo")
public class DemoController {

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private OrderProcessingService orderProcessingService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestParam String userName, @RequestParam BigDecimal amount) {
        BigDecimal finalAmount = orderProcessingService.simulateCheckout(userName, amount);

        // Fetch their new status to show the immediate reflection
        UserSubscriptionDto currentSub = membershipService.getCurrentMembership(userName);
        String currentTier = currentSub != null
                ? currentSub.getTierPlanPricing().getTier().getName()
                : "No Active Membership";

        return ResponseEntity.ok(Map.of(
                "message", "Order processed successfully!",
                "amountPaid", finalAmount,
                "userCurrentTierStatus", currentTier
        ));
    }
}
