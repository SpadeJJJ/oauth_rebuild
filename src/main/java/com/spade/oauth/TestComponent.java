package com.spade.oauth;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;


@ConfigurationProperties(prefix = "oauth2")
@Component
@Setter
public class TestComponent {

    public Map<String, String> oauth2;
    public Map<String, String> getPref() {
        return oauth2;
    }

    public void setOauth2(Map<String, String> oauth2) {
        this.oauth2 = oauth2;
    }
}
