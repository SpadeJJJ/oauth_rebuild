package com.spade.oauth.config;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.filter.OAuthServiceFilter;
import com.spade.oauth.service.OAuthTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Spring 자동 설정 - filter
 */
@RequiredArgsConstructor
@AutoConfiguration(after = OAuthAutoConfiguration.class)
public class FilterAutoConfiguration {

    private final OAuthTokenService oAuthTokenService;

    private final OAuthPathContext oAuthPathContext;

    @Bean
    public OAuthServiceFilter oAuthServiceFilter() {
        return new OAuthServiceFilter(oAuthTokenService, oAuthPathContext);
    }
}
