package com.spade.oauth.config;

import com.spade.oauth.context.StateContext;
import com.spade.oauth.redis.config.RedisConfig;
import com.spade.oauth.redis.repository.OAuthStateRepository;
import com.spade.oauth.redis.service.StateUtilService;
import com.spade.oauth.redis.service.kakao.RedisKakaoOAuthStateService;
import com.spade.oauth.redis.service.naver.RedisNaverOAuthStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Spring 자동 설정 - Redis Service 관련
 */
@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true",
        matchIfMissing = true)
//@ConditionalOnBean(RedisConfig.class)
@RequiredArgsConstructor
@EnableRedisRepositories("com.spade.oauth.redis")
@AutoConfiguration(after = RedisRepositoriesAutoConfigurationCustom.class)
public class RedisServiceAutoConfiguration {

    private final OAuthStateRepository oauthStateRepository;

    private final StateContext stateContext;
    @Bean
    public RedisNaverOAuthStateService naverRedisService() {
        return new RedisNaverOAuthStateService(oauthStateRepository);
    }

    @Bean
    public RedisKakaoOAuthStateService kakaoRedisService() {
        return new RedisKakaoOAuthStateService(oauthStateRepository);
    }

    @Bean
    public StateUtilService stateUtilService() {
        return new StateUtilService(oauthStateRepository, stateContext);
    }


}
