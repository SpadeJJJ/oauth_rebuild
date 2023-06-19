package com.spade.oauth.service.naver;


import com.spade.oauth.dto.param.ParamForAccessToken;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.feign.client.OAuthNaverClient;
import com.spade.oauth.property.NaverOAuthProperty;
import com.spade.oauth.service.OAuthService;
import feign.Feign;
import feign.codec.Encoder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * OAuth 토큰 Request(feign) Naver 구현체
 * todo
 * 이것도 공통이므로 CommonOAuthService으로 kakao, naver 통합 예정
 */
@Service("naverService")
@RequiredArgsConstructor
@DependsOn(value = {"naverOAuthProperty", "encoder"})
public class NaverOAuthService implements OAuthService {

    /** Spring Encoder */
    private final Encoder encoder;

    /** naver 관련 property */
    private final NaverOAuthProperty naverOAuthProperty;

    /** Request token 요청용 naver client */
    private OAuthNaverClient oAuthNaverClient;

    @PostConstruct
    public void setOAuthClient() {
        oAuthNaverClient = Feign.builder()
                                .contract(new SpringMvcContract())
                                .encoder(encoder)
                                .target(OAuthNaverClient.class, naverOAuthProperty.getRequestTokenUrl());
    }

    /** OAuth token 생성 요청  */
    @Override
    public String requestForAuthorizeTokenCreate(ParamForAccessToken param) {
        String result = null;

        result = oAuthNaverClient.requestAccessTokenCreate(param);
        if(result == null) {

            throw new AuthorizeFailureException("naver access token create fail");
        }

        return result;
    }

    /** authorize 요청 url 생성*/
    @Override
    public String createAuthorizeUrl() {
        UriComponents url = UriComponentsBuilder.newInstance()
                                                .path(naverOAuthProperty.getAuthorizeUrl())
                                                .queryParam("response_type", "code")
                                                .queryParam("client_id", naverOAuthProperty.getClientId())
                                                .queryParam("redirect_uri", naverOAuthProperty.getCallBackHost()+naverOAuthProperty.getCallBackUri())
                                                .queryParam("state", createRandomState())
                                                .build();

        return url.toString();
    }
}