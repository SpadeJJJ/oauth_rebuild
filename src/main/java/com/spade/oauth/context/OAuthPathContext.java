package com.spade.oauth.context;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 모든 .properties key - value 저장.
 * callback url 관리
 *
 * properties : oauth2.authorize-info.naver.host-url, https://kauth.kakao.com/oauth 형식
 * oAuthPathMap : /naver/callback(등록한 callback url), naver (기업명) 형식
 */
@RequiredArgsConstructor
@Configuration
@Setter
public class OAuthPathContext {

    @Resource(name = "properties")
    private final Map<String, String> properties;

    private Map<String, String> oAuthPathMap = new HashMap<>();

    /** oAuthPathMap init */
    @PostConstruct
    public void setOAuthPaths() {
        String pathPattern = "(\\boauth2.authorize-info.\\b)(.*)(\\b.call-back-url\\b)";
        Pattern patten = Pattern.compile(pathPattern);

        for (String key : properties.keySet()) {
            Matcher matcher = patten.matcher(key);
            if (matcher.find()) {
                String type = matcher.group(2);
                oAuthPathMap.put(properties.get(key), type);
            }
        }
    }

    public Map<String, String> getOAuthPathMap() {
        return this.oAuthPathMap;
    }

    public String findProperties(String key) {
        if (this.properties.containsKey(key)) {
            return properties.get(key);
        }

        return null;
    }

    public String match(String requestUri) {
        String result = null;

        if(oAuthPathMap.containsKey(requestUri)) {
            return oAuthPathMap.get(requestUri);
        }

        return result;
    }
}
