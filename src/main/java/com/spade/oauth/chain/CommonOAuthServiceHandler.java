package com.spade.oauth.chain;

import com.spade.oauth.manager.OAuthServiceManager;
import com.spade.oauth.dto.param.ParamForAccessToken;
import com.spade.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class CommonOAuthServiceHandler extends Chain {


    private final OAuthServiceManager oAuthServiceManager;

    @Override
    public void request(String type, Map<String, String> map,  ParamForAccessToken param) {
        OAuthService oAuthService = oAuthServiceManager.getOAuthService(type);
        String result = oAuthService.requestAccessToken(param);

        System.out.println("service chain ");
        if (checkNext()) {

            next.request(type, result, map);
        }
    }

    @Override
    public void request(String type, String result, Map<String, String> map) {

    }


    @Override
    public void request(String type, Map<String, String[]> map) {

    }

}
