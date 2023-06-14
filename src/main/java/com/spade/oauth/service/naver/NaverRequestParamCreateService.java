package com.spade.oauth.service.naver;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.dto.model.param.ParamForOAuthAuthorizeRequest;
import com.spade.oauth.service.RequestParamCreateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * OAuth 토큰 Request(feign) Naver 요청에 필요한 파라미터 토큰 생성 서비스
 */
@Service("naverParamService")
public class NaverRequestParamCreateService implements RequestParamCreateService {

    @Value("${oauth2.authorize-info.naver.client-id}")
    private String clientId;
    @Value("${oauth2.authorize-info.naver.secret-key}")
    private String clientSecret;

    @Override
    public ParamForAccessToken createParamForAccessTokenCreate(ParamForOAuthAuthorizeRequest token) {
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
        param.setClientSecret(clientSecret);
    }
}
