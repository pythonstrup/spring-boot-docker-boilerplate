package com.pythonstrup.demo.repository;

import com.pythonstrup.demo.entity.User;
import com.pythonstrup.demo.security.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // JPA Query Method
    Optional<User> findById(int id);
    Optional<UserDto> findByUsername(String username);
}
