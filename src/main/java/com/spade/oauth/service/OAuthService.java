package com.spade.oauth.service;

import com.spade.oauth.dto.model.param.ParamForAccessToken;

public interface OAuthService {

    String requestForAuthorizeTokenCreate(ParamForAccessToken param);
}
