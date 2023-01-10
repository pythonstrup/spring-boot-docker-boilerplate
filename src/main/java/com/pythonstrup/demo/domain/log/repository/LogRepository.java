package com.pythonstrup.demo.domain.log.repository;

import com.pythonstrup.demo.domain.log.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
