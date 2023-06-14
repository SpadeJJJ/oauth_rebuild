package com.spade.oauth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class PropertiesDto {
    private String callBackUrl;
    private String hostUrl;
    private String authorizeUrl;
    private String clientId;
    private String secretKey;
}
