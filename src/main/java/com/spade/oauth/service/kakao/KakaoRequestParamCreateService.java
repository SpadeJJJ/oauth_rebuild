package com.spade.oauth.service.kakao;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.dto.model.param.ParamForStateInfo;
import com.spade.oauth.service.RequestParamCreateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KakaoRequestParamCreateService implements RequestParamCreateService {

    @Value("${oauth2.kakao.clientid}")
    private String clientId;

    @Override
    public ParamForAccessToken createParamForAccessTokenCreate(ParamForStateInfo token) {
        ParamForAccessToken param = new ParamForAccessToken();

        setCommonParam(param);
        param.setGrantType("authorization_code");
        param.setState(token.getState());
        param.setCode(token.getCode());

        return param;
    }

    @Override
    public void setCommonParam(ParamForAccessToken param) {
        param.setClientId(clientId);
    }
}
