package com.spade.oauth.config;

import com.spade.oauth.context.OAuthPathMapper;
import com.spade.oauth.event.OAuthResultService;
import com.spade.oauth.filter.OAuthServiceFilter;
import com.spade.oauth.service.OAuthTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@AutoConfiguration(after = OAuthAutoConfiguration.class)
public class FilterAutoConfiguration {

    private final OAuthPathMapper oAuthPathMapper;

    private final OAuthTokenService oAuthTokenService;

    private final OAuthResultService oAuthResultService;


    @Bean
    public OAuthServiceFilter oAuthServiceFilter() {
        return new OAuthServiceFilter(oAuthPathMapper, oAuthTokenService, oAuthResultService);
    }
}
