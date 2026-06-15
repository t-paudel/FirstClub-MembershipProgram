package com.firstclub.membershipprogram.controllers;

import com.firstclub.membershipprogram.dtos.CheckoutDto;
import com.firstclub.membershipprogram.services.OrderProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/apis/demo")
public class DemoController {

    @Autowired
    private OrderProcessingService orderProcessingService;

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutDto> checkout(@RequestParam String userName, @RequestParam BigDecimal amount) {
        try {
            CheckoutDto checkout = orderProcessingService.simulateCheckout(userName, amount);
            checkout.setMessage("Order processed successfully!");

            return ResponseEntity.ok(checkout);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
