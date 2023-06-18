package com.spade.oauth.config;

import com.spade.oauth.redis.config.RedisConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Spring 자동 설정 - Redis 관련
 */
@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true",
        matchIfMissing = true)
//@ConditionalOnProperty({"spring.cache.redis.host", "spring.cache.redis.port"})
@RequiredArgsConstructor
@AutoConfiguration
public class RedisAutoConfigurationCustom extends RedisAutoConfiguration {

//    @Bean
//    RedisConfig redisConfig() {
//        return new RedisConfig();
//    }
}
