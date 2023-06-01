package com.spade.oauth.service;

import com.spade.oauth.dto.model.ParamAccessToken;

import java.util.Map;

public interface RequestParamCreateService {

    public ParamAccessToken createParamForAccessTokenCreate(String code, String responseState);

    public ParamAccessToken createParamForAccessTokenUpdate();

    public ParamAccessToken createParamForAccessTokenDelete();
}
