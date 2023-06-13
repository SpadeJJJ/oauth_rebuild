package com.spade.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;


//@ConfigurationProperties(prefix = "test")
@ConfigurationProperties(prefix = "propertyprefix")
@Component
@Setter
@Getter
@ToString
public class TestComponent {

    public Map<String, TestDTo> var;
//    String test1;
//    String test2;
//    public Map<String, List<String>> getPref() {
//        return oauth2;
//    }

//    public void setOauth2(Map<String, List<String>> oauth2) {
//        this.oauth2 = oauth2;
//    }
}
