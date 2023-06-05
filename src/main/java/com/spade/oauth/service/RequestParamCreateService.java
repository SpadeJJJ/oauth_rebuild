package com.spade.oauth.service;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.dto.model.param.ParamForStateInfo;

public interface RequestParamCreateService {

    public ParamForAccessToken createParamForAccessTokenCreate(ParamForStateInfo token);
    public void setCommonParam(ParamForAccessToken param);
}
