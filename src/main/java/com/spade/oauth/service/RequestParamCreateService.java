package com.spade.oauth.service;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.dto.model.param.ParamForOAuthAuthorizeRequest;

/**
 * OAuth 토큰 Request(feign) 요청에 필요한 파리미터 토큰 생성 공통 인터페이스
 */

public interface RequestParamCreateService {

    public ParamForAccessToken createParamForAccessTokenCreate(ParamForOAuthAuthorizeRequest token);
    public void setCommonParam(ParamForAccessToken param);
}
