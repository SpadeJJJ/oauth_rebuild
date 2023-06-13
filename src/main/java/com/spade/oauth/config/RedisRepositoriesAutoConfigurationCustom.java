package com.spade.oauth.config;


import com.spade.oauth.redis.config.RedisConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

/**
 * Spring 자동 설정 - Redis Repository 관련
 */
@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true",
        matchIfMissing = true)
//@ConditionalOnBean(RedisConfig.class)
@AutoConfiguration(after = RedisAutoConfigurationCustom.class)
public class RedisRepositoriesAutoConfigurationCustom extends RedisRepositoriesAutoConfiguration {
}
