package com.spade.oauth.service.naver;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.feign.client.NaverOAuthClient;
import com.spade.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * OAuth 토큰 Request(feign) Naver 구현체
 */
@Service("naverService")
@RequiredArgsConstructor
public class NaverOAuthService implements OAuthService {

    private final NaverOAuthClient naverOauthClient;

    @Override
    public String requestForAuthorizeTokenCreate(ParamForAccessToken param) {
        String result = null;

        result = naverOauthClient.requestAccessTokenCreate(param);
        if(result == null) {
            throw new AuthorizeFailureException("naver access token create fail");
        }

        return result;
    }
}