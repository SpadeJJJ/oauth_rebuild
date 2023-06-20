package com.spade.oauth.filter;

import com.spade.oauth.manager.OAuthManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Map;

/**
 * OAuthServiceFilter 등록 Config
 */
@Configuration
@RequiredArgsConstructor
@DependsOn(value = {"OAuthManagerFactory"})
public class FilterConfig {

    /** 임시 주석처리 */
//    private final OAuthTokenServiceFactory oAuthTokenServiceFactory;
    /** call back url등 path를 관리하는 Context */
    private final OAuthManagerFactory oAuthManagerFactory;


    /** Filter 등록 */
    @Bean
    public FilterRegistrationBean<OAuthServiceFilter> filter() {
        FilterRegistrationBean<OAuthServiceFilter> bean = new FilterRegistrationBean<>();
        Map<String ,String> urls = oAuthManagerFactory.getOAuthPropertyManager().getCallbackUriList();
        bean.setUrlPatterns(urls.keySet().stream().toList());
        bean.setFilter(new OAuthServiceFilter(oAuthManagerFactory.getOAuthChainManager(), urls));

        return bean;
    }
}
