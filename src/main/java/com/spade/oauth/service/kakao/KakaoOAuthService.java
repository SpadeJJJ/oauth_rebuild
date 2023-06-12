package com.spade.oauth.service.kakao;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.feign.client.KakaoOAuthClient;
import com.spade.oauth.service.OAuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("kakaoService")
@RequiredArgsConstructor
public class KakaoOAuthService implements OAuthService {

    private final KakaoOAuthClient kakaoOauthClient;

    @Override
    public String requestForAuthorizeTokenCreate(ParamForAccessToken param) {
        String result = null;

        result = kakaoOauthClient.requestAccessTokenCreate(param);
        if(result == null) {
            throw new AuthorizeFailureException("kakao access token create fail");
        }

        return result;
    }
}
