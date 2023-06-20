package com.spade.oauth.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class OAuthProperty {
    private String callBackUri;
    private String requestTokenUrl;
    private String clientId;
    private String secretKey;
    private String grantType;
    private String authorizeUrl;
    private String callBackHost;

}
