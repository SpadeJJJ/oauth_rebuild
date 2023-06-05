package com.spade.oauth.service;

import com.spade.oauth.dto.model.param.ParamForAccessToken;

import java.lang.reflect.InvocationTargetException;

public interface OauthService {

    OauthService from(String type) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    String requestForAuthorizeTokenCreate(ParamForAccessToken param);
}
