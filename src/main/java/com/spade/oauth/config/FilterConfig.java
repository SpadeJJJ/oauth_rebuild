package com.spade.oauth.config;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.context.OAuthPathMapper;
import com.spade.oauth.event.OAuthResultService;
import com.spade.oauth.filter.OAuthServiceFilter;
import com.spade.oauth.service.OAuthTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final OAuthPathMapper oAuthPathMapper;

    private final OAuthTokenService oAuthTokenService;

    private final OAuthResultService oAuthResultService;

    private final OAuthPathContext oAuthPathContext;

    @Bean
    public FilterRegistrationBean<OAuthServiceFilter> oauthServiceFilter() {
        FilterRegistrationBean<OAuthServiceFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new OAuthServiceFilter(oAuthPathMapper, oAuthTokenService, oAuthResultService));
        registrationBean.setUrlPatterns(oAuthPathContext.toList());
        registrationBean.setName("oAuthServiceFilter");
        return registrationBean;
    }
}
