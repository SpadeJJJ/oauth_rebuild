package com.spade.oauth.redis.config;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.context.StateContext;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Redis 설정 클래스
 *
 * todo
 * 이거 만약에 host, port 둘 중에 하나라도 null이면
 * redis 사용 안 하게 설정해야 함.
 */
//@EnableRedisRepositories
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final OAuthPathContext oAuthPathContext;

    private final StateContext stateContext;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        if (oAuthPathContext.findProperties("oauth2.authorize-info.reids.host") != null &&
                oAuthPathContext.findProperties("oauth2.authorize-info.reids.port") != null) {
            stateContext.setRedisUse(true);
            return new LettuceConnectionFactory(oAuthPathContext.findProperties("oauth2.authorize-info.reids.host"),
                    Integer.parseInt(oAuthPathContext.findProperties("oauth2.authorize-info.reids.port")));
        }

        stateContext.setRedisUse(false);
        return null;
    }
}
