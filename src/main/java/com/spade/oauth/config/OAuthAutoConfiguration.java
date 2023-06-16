package com.spade.oauth.config;

import com.spade.oauth.context.StateContext;
import com.spade.oauth.feign.client.KakaoOAuthClient;
import com.spade.oauth.feign.client.NaverOAuthClient;
import com.spade.oauth.service.OAuthTokenService;
import com.spade.oauth.service.kakao.KakaoOAuthService;
import com.spade.oauth.service.naver.NaverOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

/**
 * Spring 자동 설정 - OAuth
 */
@ConditionalOnProperty(name = "oauth", havingValue = "on")
@AutoConfiguration(after = StateAutoConfiguration.class)
@RequiredArgsConstructor
//@EnableRedisRepositories("com.spade.oauth.redis")
@EnableFeignClients("com.spade.oauth.feign")
public class OAuthAutoConfiguration {

    private final StateContext stateContext;

    private final ApplicationEventPublisher applicationEventPublisher;
    private final NaverOAuthClient naverOauthClient;

    private final KakaoOAuthClient kakaoOauthClient;


    @Bean
    public OAuthTokenService oAuthTokenService(ApplicationContext applicationContext) {

        return new OAuthTokenService(applicationContext, stateContext, applicationEventPublisher);
    }

    @Bean
    public NaverOAuthService naverService() {
        return new NaverOAuthService(naverOauthClient);
    }

    @Bean
    public KakaoOAuthService kakaoService() {
        return new KakaoOAuthService(kakaoOauthClient);
    }


}
