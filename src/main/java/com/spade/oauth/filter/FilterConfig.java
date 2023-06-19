package com.spade.oauth.filter;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.service.OAuthTokenServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * OAuthServiceFilter 등록 Config
 */
@Configuration
@RequiredArgsConstructor
@DependsOn(value = {"OAuthTokenServiceFactory", "OAuthPathContext"})
public class FilterConfig {

    /** Request token을 처리하는 서비스 */
    private final OAuthTokenServiceFactory oAuthTokenServiceFactory;
    /** call back url등 path를 관리하는 Context */
    private final OAuthPathContext oAuthPathContext;

    /** Filter 등록 */
    @Bean
    public FilterRegistrationBean<OAuthServiceFilter> filter() {
        FilterRegistrationBean<OAuthServiceFilter> bean = new FilterRegistrationBean<>();
        bean.setUrlPatterns(oAuthPathContext.getOAuthPathMap().keySet().stream().toList());
        bean.setFilter(new OAuthServiceFilter(oAuthTokenServiceFactory, oAuthPathContext));

        return bean;
    }
}
