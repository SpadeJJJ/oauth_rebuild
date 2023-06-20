package com.spade.oauth.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Kakao 관련 property 관리 클래스
 * 삭제 예정
 */
//@ConfigurationProperties(prefix = "oauth2.authorize-info.kakao")
//@Component
@Getter
@Setter
public class KakaoOAuthProperty {
    private String callBackUri;
    private String requestTokenUrl;
    private String clientId;
    private String grantType;
    private String authorizeUrl;
    private String callBackHost;
}
