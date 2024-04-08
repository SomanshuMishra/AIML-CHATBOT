package com.aimlpolestar.aimlPolestar.profile.repository;

import com.aimlpolestar.aimlPolestar.profile.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {
    Optional<UserDetails> findByMobno(String mobno);

    Optional<UserDetails> findByPrimaryuser(String primaryuser);
}

