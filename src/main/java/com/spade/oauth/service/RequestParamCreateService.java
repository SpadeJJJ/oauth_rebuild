package com.spade.oauth.service;

import com.spade.oauth.dto.model.param.ParamAccessToken;
import com.spade.oauth.dto.model.param.StateInfoForParam;

public interface RequestParamCreateService {

    public ParamAccessToken createParamForAccessTokenCreate(StateInfoForParam token);

    public ParamAccessToken createParamForAccessTokenUpdate(StateInfoForParam token);

    public ParamAccessToken createParamForAccessTokenDelete(StateInfoForParam token);
}
