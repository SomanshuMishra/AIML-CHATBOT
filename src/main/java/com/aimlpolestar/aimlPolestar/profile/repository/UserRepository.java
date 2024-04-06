package com.aimlpolestar.aimlPolestar.repository;

import com.aimlpolestar.aimlPolestar.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {
    Optional<UserDetails> findByMobno(String mobno);
}

