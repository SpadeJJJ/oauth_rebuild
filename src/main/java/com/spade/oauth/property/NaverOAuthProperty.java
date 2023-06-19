package com.spade.oauth.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Naver 관련 property 관리 클래스
 */
@ConfigurationProperties(prefix = "oauth2.authorize-info.naver")
@Component
@Getter
@Setter
public class NaverOAuthProperty {
    private String callBackUri;
    private String requestTokenUrl;
    private String clientId;
    private String secretKey;
    private String grantType;
    private String authorizeUrl;
    private String callBackHost;

}
