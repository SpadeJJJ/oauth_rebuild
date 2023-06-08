package com.spade.oauth.config;

import com.spade.oauth.context.OAuthPathMapper;
import com.spade.oauth.context.OAuthPathRepository;
import com.spade.oauth.event.OAuthResultEventHandler;
import com.spade.oauth.event.OAuthResultEventHandlerImpl;
import com.spade.oauth.event.OAuthResultService;
import com.spade.oauth.fegin.client.KakaoOauthClient;
import com.spade.oauth.fegin.client.NaverOauthClient;
import com.spade.oauth.redis.config.RedisConfig;
import com.spade.oauth.redis.repository.OauthStateRepository;
import com.spade.oauth.redis.service.kakao.RedisKakaoOauthStateService;
import com.spade.oauth.redis.service.naver.RedisNaverOauthStateService;
import com.spade.oauth.service.OAuthTokenService;
import com.spade.oauth.service.kakao.KakaoOauthService;
import com.spade.oauth.service.kakao.KakaoRequestParamCreateService;
import com.spade.oauth.service.naver.NaverOauthService;
import com.spade.oauth.service.naver.NaverRequestParamCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
@ConditionalOnProperty(name = "oauth", havingValue = "on")
@RequiredArgsConstructor
@AutoConfiguration
@ComponentScan("com.spade.oauth.redis")
public class AutoConfig {

    @Value("${oauth2.authorize.callback.urls}")
    List<String> paths;

    private final OauthStateRepository oauthStateRepository;


    @Bean
    public NaverOauthService naverService() {
        return new NaverOauthService();
    }

    @Bean
    public KakaoOauthService kakaoService() {
        return new KakaoOauthService();
    }

    @Bean
    public RedisNaverOauthStateService naverRedisService() {
        return new RedisNaverOauthStateService(oauthStateRepository);
    }

    @Bean
    public RedisKakaoOauthStateService kakaoRedisService() {
        return new RedisKakaoOauthStateService(oauthStateRepository);
    }

    @Bean
    public OAuthPathMapper oAuthPathMapper() {
        return new OAuthPathMapper(oAuthPathRepository(paths));
    }

    @Bean
    public OAuthPathRepository oAuthPathRepository(List<String> paths) {
        return new OAuthPathRepository(paths);
    }

    @Bean
    public OAuthTokenService oAuthTokenService(ApplicationContext applicationContext) {
        return  new OAuthTokenService(applicationContext);
    }

    @Bean
    public OAuthResultService oAuthResultService(ApplicationEventPublisher applicationEventPublisher) {
        return new OAuthResultService(applicationEventPublisher);
    }

    @Bean
    public NaverRequestParamCreateService naverParamService() {
        return new NaverRequestParamCreateService();
    }

    @Bean
    public KakaoRequestParamCreateService kakaoParamService() {
        return new KakaoRequestParamCreateService();
    }

    @Bean
    RedisConfig redisConfig() {
        return new RedisConfig();
    }

//    @Bean
//    OAuthResultEventHandler oAuthResultEventHandler() {
//        return new OAuthResultEventHandlerImpl();
//    }

}
