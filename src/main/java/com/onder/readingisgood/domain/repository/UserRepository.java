package com.onder.readingisgood.domain.repository;

import com.onder.readingisgood.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserEntitiesByEmail(String email);
    Boolean existsByEmail(String email);

}
