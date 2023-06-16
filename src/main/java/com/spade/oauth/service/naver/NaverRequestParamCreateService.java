package com.spade.oauth.service.naver;

import com.spade.oauth.context.OAuthPathContext;
import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.dto.model.param.ResultParamForOAuthLoginRequest;
import com.spade.oauth.service.RequestParamCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * OAuth 토큰 Request(feign) Naver 요청에 필요한 파라미터 토큰 생성 서비스
 *
 * todo
 * 이거 통합 및 변경해야 함.
 * 일단 이 서비스 자체가 필요가 없을 것 같음.
 */
@Service("naverParamService")
@RequiredArgsConstructor
public class NaverRequestParamCreateService implements RequestParamCreateService {

    @Value("${oauth2.authorize-info.naver.client-id}")
    private String clientId;
    @Value("${oauth2.authorize-info.naver.secret-key}")
    private String clientSecret;

//    private final OAuthPathContext oAuthPathContext;

    private final String PROPERTIES_KEY_PREFIX = "oauth2.authorize-info.";
    private final String CLIENT_ID = "client_id";
    private final String GRANT_TYPE = "grant_type";
    private final String SECRET_KEY = "secret_key";

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
        param.setClientSecret(clientSecret);
    }

//    public String createOAuthAuthorizeRequestParam(String type, String state, String code) {
//
//        StringBuilder result = new StringBuilder();
//        result.append("?");
//
//        if (oAuthPathContext.findProperties(PROPERTIES_KEY_PREFIX+type+"."+GRANT_TYPE) != null) {
//            result.append(GRANT_TYPE);
//            result.append("=");
//            result.append(oAuthPathContext.findProperties(PROPERTIES_KEY_PREFIX+type+"."+GRANT_TYPE));
//            result.append("&");
//        } else {
//            throw new NullPointerException(type + " grant_type properties is null");
//        }
//
//        result.append("state="+state);
//        result.append("&");
//        result.append("code="+code);
//
//        return result.toString();
//    }

}
