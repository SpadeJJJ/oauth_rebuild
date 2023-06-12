package com.spade.oauth.config;


import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true",
        matchIfMissing = true)
@AutoConfiguration(after = RedisAutoConfigurationCustom.class)
public class RedisRepositoriesAutoConfigurationCustom extends RedisRepositoriesAutoConfiguration {
}
