package com.pythonstrup.demo.configuration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {

    private final StringRedisTemplate template;

    public RedisConfig(StringRedisTemplate template) {
        this.template = template;
    }
}

