package com.spade.oauth.autoconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

//    private final OAuthStateRepository oauthStateRepository;
//
//    private final StateContext stateContext;
//    @Bean
//    public RedisNaverOAuthStateService naverRedisService() {
//        return new RedisNaverOAuthStateService(oauthStateRepository);
//    }
//
//    @Bean
//    public RedisKakaoOAuthStateService kakaoRedisService() {
//        return new RedisKakaoOAuthStateService(oauthStateRepository);
//    }
//
//    @Bean
//    public StateUtilService stateUtilService() {
//        return new StateUtilService(oauthStateRepository, stateContext);
//    }


}
