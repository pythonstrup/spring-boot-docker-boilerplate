package com.pythonstrup.demo.domain.user.repository;

import com.pythonstrup.demo.domain.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
