package com.pythonstrup.demo.repository;

import com.pythonstrup.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // JPA Query Method
    User findByUsername(String username);
}
