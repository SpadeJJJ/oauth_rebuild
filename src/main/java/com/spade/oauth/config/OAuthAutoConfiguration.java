package com.spade.oauth.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@Configuration
@ConditionalOnProperty(name = "oauth", havingValue = "on")
@AutoConfiguration(after = StateAutoConfiguration.class)
@RequiredArgsConstructor
//@EnableRedisRepositories("com.spade.oauth.redis")
@EnableFeignClients("com.spade.oauth.feign")
public class OAuthAutoConfiguration {
//
//    @Value("${oauth2.authorize.callback.urls}")
//    private final List<String> paths;
//
//    private final NaverOAuthClient naverOauthClient;
//
//    private final KakaoOAuthClient kakaoOauthClient;
//
//    private final StateContext stateContext;
//
//    @Bean
//    public OAuthTokenService oAuthTokenService(ApplicationContext applicationContext) {
//
//        return new OAuthTokenService(applicationContext, stateContext);
//    }
//
//    @Bean
//    public NaverOAuthService naverService() {
//        return new NaverOAuthService(naverOauthClient);
//    }
//
//    @Bean
//    public KakaoOAuthService kakaoService() {
//        return new KakaoOAuthService(kakaoOauthClient);
//    }
//
//    @Bean
//    public OAuthPathMapper oAuthPathMapper() {
//        return new OAuthPathMapper(oAuthPathRepository(paths));
//    }
//
//    @Bean
//    public OAuthPathRepository oAuthPathRepository(List<String> paths) {
//        return new OAuthPathRepository(paths);
//    }
//
//    @Bean
//    public OAuthResultService oAuthResultService(ApplicationEventPublisher applicationEventPublisher) {
//        return new OAuthResultService(applicationEventPublisher);
//    }

}
