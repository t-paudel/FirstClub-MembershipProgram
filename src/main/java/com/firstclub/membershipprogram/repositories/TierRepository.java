package com.firstclub.membershipprogram.repositories;

import com.firstclub.membershipprogram.entities.MembershipTierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierRepository extends JpaRepository<MembershipTierEntity, Long> {
}
