package com.spade.oauth.service.kakao;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.dto.model.param.ResultParamForOAuthLoginRequest;
import com.spade.oauth.service.RequestParamCreateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * OAuth 토큰 Request(feign) Kakao 요청에 필요한 파리미터 토큰 생성 서비스
 */

@Service("kakaoParamService")
public class KakaoRequestParamCreateService implements RequestParamCreateService {

    @Value("${oauth2.authorize-info.kakao.client-id}")
    private String clientId;

    @Override
    public ParamForAccessToken createParamForAccessTokenCreate(ResultParamForOAuthLoginRequest token) {
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
