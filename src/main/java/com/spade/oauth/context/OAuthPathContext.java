package com.spade.oauth.context;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * OAuthPath 관리 클래스
 * oAuthPathMap : /naver/callback(등록한 callback url), naver (기업명) 형식
 */
@RequiredArgsConstructor
@Configuration
@Setter
@DependsOn(value = {"propertiesResourceConfig"})
public class OAuthPathContext {

    /** properties : oauth2.authorize-info.naver.host-url, https://kauth.kakao.com/oauth 형식으로 모든 properties의 key-value 관리 */
    @Resource(name = "properties")
    private final Map<String, String> properties;

    /** oAuthPathMap : 모든 callback url 관리. naver/callback, naver 형식.  */
    private Map<String, String> oAuthPathMap = new HashMap<>();

    /** oAuthPathMap 등록
     * uri, type 형태로 저장.
     * ex. callback/naver, naver
     */
    @PostConstruct
    public void setOAuthPaths() {
        String pathPattern = "(\\boauth2.authorize-info.\\b)(.*)(\\b.call-back-uri\\b)";
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

    /**
     *  .properties의 value 검색.
     *   ex. key = oauth2.authorize-info.kakao.grant-type -> return authorization_code
     **/
    public String findProperties(String key) {
        if (this.properties.containsKey(key)) {
            return properties.get(key);
        }

        return null;
    }

    /**
     *  requestUri로 type(기업명) 검색
     *  ex. requestUri = /naver/callback -> return naver
     **/
    public String getType(String requestUri) {
        String result = null;

        if(oAuthPathMap.containsKey(requestUri)) {
            return oAuthPathMap.get(requestUri);
        }

        return result;
    }
}
