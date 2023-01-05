package com.pythonstrup.demo.repository;

import com.pythonstrup.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // JPA Query Method
    Optional<User> findByUsername(String username);
}
