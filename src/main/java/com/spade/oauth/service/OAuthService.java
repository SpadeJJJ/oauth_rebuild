package com.spade.oauth.service;

import com.spade.oauth.dto.model.param.ParamForAccessToken;

/**
 * OAuth 토큰 요청 Request(feign) 공통 인터페이스
 */
public interface OAuthService {

    String requestForAuthorizeTokenCreate(ParamForAccessToken param);
}
