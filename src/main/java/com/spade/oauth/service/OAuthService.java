package com.spade.oauth.service;

import com.spade.oauth.dto.param.ParamForAccessToken;
import com.spade.oauth.property.OAuthProperty;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Request token 공통 인터페이스
 */
public interface OAuthService {

    public String requestAccessToken(ParamForAccessToken param);

//    void setOAuthClient();

//    String createAuthorizeUrl();

    /** state 랜덤 생성 */
    default String createRandomState() {
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();

        return state;
    }
}
