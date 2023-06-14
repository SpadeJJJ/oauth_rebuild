package com.spade.oauth.context;

import com.spade.oauth.PropertiesDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ConfigurationProperties 규칙
 * 1. prefix option
 * 2. @Component
 * 3. 변수명 == properties의 var와 매치
 *  (ex. property.authorize.naver.host-url)
 */
@ConfigurationProperties(prefix = "oauth2")
@Component
@Setter
@Getter
public class OAuthPathContext {
    private Map<String, PropertiesDto> authorizeInfo;

    public List<String> toList() {
        ArrayList<String> result = new ArrayList<>();

        for (String key : authorizeInfo.keySet()) {
            result.add(authorizeInfo.get(key).getCallBackUrl());
        }

        return result;
    }


}
