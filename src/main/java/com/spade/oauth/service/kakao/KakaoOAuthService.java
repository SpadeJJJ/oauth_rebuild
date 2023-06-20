package com.spade.oauth.service.kakao;

import com.spade.oauth.dto.param.ParamForAccessToken;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.feign.client.OAuthKakaoClient;
import com.spade.oauth.feign.client.OAuthNaverClient;
import com.spade.oauth.property.KakaoOAuthProperty;
import com.spade.oauth.property.NaverOAuthProperty;
import com.spade.oauth.property.OAuthProperty;
import com.spade.oauth.service.OAuthService;

import feign.Feign;
import feign.codec.Encoder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Request token 처리 서비스
 */
//@Service("kakaoService")
@RequiredArgsConstructor
public class KakaoOAuthService {

    /** Spring Encoder */
    private final Encoder encoder;

    /** kakao 관련 property */
    private final KakaoOAuthProperty kakaoOAuthProperty;

    /** Request token 요청용 kakao client */
    private OAuthKakaoClient oAuthKakaoClient;

    /** kakao 수동 등록 */
    @PostConstruct
    public void setOAuthClient() {
        oAuthKakaoClient = Feign.builder()
                               .contract(new SpringMvcContract())
                               .encoder(encoder)
                               .target(OAuthKakaoClient.class, kakaoOAuthProperty.getRequestTokenUrl());
    }

    /** OAuth token 생성 요청  */
    public String requestAccessToken(ParamForAccessToken param) {
        String result = null;

        result = oAuthKakaoClient.requestAccessTokenCreate(param);
        if(result == null) {
            throw new AuthorizeFailureException("kakao access token create fail");
        }

        return result;
    }

    /** authorize 요청 url 생성*/
    public String createAuthorizeUrl() {
        UriComponents uri = UriComponentsBuilder.newInstance()
                                                .path(kakaoOAuthProperty.getAuthorizeUrl())
                                                .queryParam("response_type", "code")
                                                .queryParam("client_id", kakaoOAuthProperty.getClientId())
                                                .queryParam("redirect_uri", kakaoOAuthProperty.getCallBackHost()+kakaoOAuthProperty.getCallBackUri())
                                                .queryParam("state", "d")
                                                .build();

        return uri.toString();
    }
    public OAuthProperty getoAuthProperty() {
        return new OAuthProperty();
    }
}
