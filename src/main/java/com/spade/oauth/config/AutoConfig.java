package com.spade.oauth.config;

import com.spade.oauth.OauthRebuildApplication;
import com.spade.oauth.context.OAuthPathMapper;
import com.spade.oauth.context.OAuthPathRepository;
import com.spade.oauth.context.StateContext;
import com.spade.oauth.domain.redis.OauthState;
import com.spade.oauth.event.OAuthResultEventHandler;
import com.spade.oauth.event.OAuthResultEventHandlerImpl;
import com.spade.oauth.event.OAuthResultService;
import com.spade.oauth.fegin.client.KakaoOauthClient;
import com.spade.oauth.fegin.client.NaverOauthClient;
import com.spade.oauth.filter.OAuthServiceFilter;
import com.spade.oauth.redis.config.RedisConfig;
import com.spade.oauth.redis.repository.OauthStateRepository;
import com.spade.oauth.redis.service.StateUtilService;
import com.spade.oauth.redis.service.kakao.RedisKakaoOauthStateService;
import com.spade.oauth.redis.service.naver.RedisNaverOauthStateService;
import com.spade.oauth.service.OAuthTokenService;
import com.spade.oauth.service.kakao.KakaoOauthService;
import com.spade.oauth.service.kakao.KakaoRequestParamCreateService;
import com.spade.oauth.service.naver.NaverOauthService;
import com.spade.oauth.service.naver.NaverRequestParamCreateService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

//@Configuration
@ConditionalOnProperty(name = "oauth", havingValue = "on")
@AutoConfiguration
@RequiredArgsConstructor
@EnableRedisRepositories("com.spade.oauth.redis")
@EnableFeignClients("com.spade.oauth.fegin")
public class AutoConfig {

    @Value("${oauth2.authorize.callback.urls}")
    List<String> paths;

    private final OauthStateRepository oauthStateRepository;

    private final NaverOauthClient naverOauthClient;

    private final KakaoOauthClient kakaoOauthClient;

    @Bean
    public StateContext stateContext() {
        return new StateContext();
    }
    @Bean
    public NaverOauthService naverService() {
        return new NaverOauthService(naverOauthClient);
    }

    @Bean
    public KakaoOauthService kakaoService() {
        return new KakaoOauthService(kakaoOauthClient);
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
        return new OAuthTokenService(applicationContext, stateContext());
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
    public StateUtilService stateUtilService() {
        return new StateUtilService(oauthStateRepository, stateContext());
    }

    @Bean
    public OAuthServiceFilter oAuthServiceFilter(ApplicationContext applicationContext, ApplicationEventPublisher applicationEventPublisher) {
        return new OAuthServiceFilter(oAuthPathMapper(), oAuthTokenService(applicationContext), oAuthResultService(applicationEventPublisher));
    }
}
