package com.spade.oauth.autoconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * Spring 자동 설정 - filter
 */
@RequiredArgsConstructor
@AutoConfiguration(after = OAuthAutoConfiguration.class)
public class FilterAutoConfiguration {
//
//    private final OAuthTokenService oAuthTokenService;
//
//    private final OAuthPathContext oAuthPathContext;
//
//    @Bean
//    public OAuthServiceFilter oAuthServiceFilter() {
//        return new OAuthServiceFilter(oAuthTokenService, oAuthPathContext);
//    }
}
