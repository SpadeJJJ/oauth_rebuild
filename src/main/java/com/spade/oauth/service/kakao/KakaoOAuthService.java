package com.spade.oauth.service.kakao;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.feign.client.OAuthClient;
import com.spade.oauth.service.OAuthService;

import feign.Feign;
import feign.codec.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Service;

/**
 * OAuth 토큰 Request(feign) Kakao 구현체
 *
 * todo
 * 통합 예정
 */
@Service("kakaoService")
@RequiredArgsConstructor
public class KakaoOAuthService implements OAuthService {

    private final Encoder encoder;

    @Override
    public String requestForAuthorizeTokenCreate(ParamForAccessToken param, String url) {
        String result = null;

        OAuthClient oAuthClient = Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(encoder)
                .target(OAuthClient.class, url);

        result = oAuthClient.requestAccessTokenCreate(param);
        if(result == null) {
            throw new AuthorizeFailureException("kakao access token create fail");
        }

        return result;
    }
}
