package com.firstclub.membershipprogram.services;

import com.firstclub.membershipprogram.repositories.TierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TierServiceImpl implements TierService {

    @Autowired
    TierRepository tierRepository;

    @Override
    public void addTier() {

    }
}
