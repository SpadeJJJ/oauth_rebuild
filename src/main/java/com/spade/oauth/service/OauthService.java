package com.spade.oauth.service;

import com.spade.oauth.dto.model.param.ParamForAccessToken;

import java.lang.reflect.InvocationTargetException;

public interface OauthService {

    String requestForAuthorizeTokenCreate(ParamForAccessToken param);
}
