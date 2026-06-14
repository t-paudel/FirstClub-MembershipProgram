package com.firstclub.membershipprogram.repositories;

import com.firstclub.membershipprogram.entities.MembershipTierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipTierRepository extends JpaRepository<MembershipTierEntity, Long> {

    List<MembershipTierEntity> findAllByOrderByTierLevelAsc();

    Optional<MembershipTierEntity> findByName(String name);
}
