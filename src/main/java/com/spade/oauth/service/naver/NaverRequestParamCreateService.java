package com.spade.oauth.service.naver;

import com.spade.oauth.dto.model.ParamAccessToken;
import com.spade.oauth.service.RequestParamCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NaverRequestParamCreateService implements RequestParamCreateService {

    @Autowired
    Environment environment;
    @Override
    public ParamAccessToken createParamForAccessTokenCreate(String code, String responseState) {
        ParamAccessToken param = new ParamAccessToken();

        param.setClientId(environment.getProperty("oauth2.naver.clientid"));
        param.setClientSecret(environment.getProperty("oauth2.naver.secretkey"));
        param.setGrantType(environment.getProperty("oauth2.naver.granttype.create"));
        param.setState(responseState);
        param.setCode(code);
        System.out.println("code : "+code);
        return param;
    }

    @Override
    public ParamAccessToken createParamForAccessTokenUpdate() {
        return null;
    }

    @Override
    public ParamAccessToken createParamForAccessTokenDelete() {
        return null;
    }
}
