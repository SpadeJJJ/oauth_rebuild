package com.spade.oauth.context;

import com.spade.oauth.dto.model.param.ParamForCallBack;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OAuth 타입(기업)
 */
public enum OAuthType {

    NAVER("naver"), KAKAO("kakao");

    private String type;

    OAuthType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.type;
    }

    public static OAuthType getType(OAuthPathContext oAuthPathContext, String path) {
        for(OAuthType oAuthType : values()) {
            if (path.contains(oAuthType.getName())) {
                return oAuthType;
            }
        }
        return null;
    }
}
