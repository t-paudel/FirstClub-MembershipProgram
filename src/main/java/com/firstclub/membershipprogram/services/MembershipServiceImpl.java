package com.firstclub.membershipprogram.services;

import com.firstclub.membershipprogram.dtos.MembershipPlanDto;
import com.firstclub.membershipprogram.mappers.MembershipPlanMapper;
import com.firstclub.membershipprogram.repositories.MembershipRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    MembershipPlanMapper mapper;

    public List<MembershipPlanDto> getPlans() {
        return membershipRepository.findAll().stream().map(mapper::map).toList();
    }
}
