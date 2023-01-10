package com.pythonstrup.demo.domain.user.repository;

import com.pythonstrup.demo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // JPA Query Method
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
}
