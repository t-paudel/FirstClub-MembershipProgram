package com.firstclub.membershipprogram.controllers;

import com.firstclub.membershipprogram.dtos.MembershipPlanDto;
import com.firstclub.membershipprogram.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/apis/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping("/plans")
    public List<MembershipPlanDto> getPlans() {
        System.out.println("Hello");
        return membershipService.getPlans();
    }
}
