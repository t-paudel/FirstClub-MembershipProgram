package com.firstclub.membershipprogram.repositories;

import com.firstclub.membershipprogram.entities.UserMembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMembershipRepository extends JpaRepository<UserMembershipEntity, String> {
}
