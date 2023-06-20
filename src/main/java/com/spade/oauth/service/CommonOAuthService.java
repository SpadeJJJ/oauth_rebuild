package com.spade.oauth.service;

import com.spade.oauth.dto.param.ParamForAccessToken;
import com.spade.oauth.exception.AuthorizeFailureException;
import com.spade.oauth.feign.client.CommonOAuthClient;
import com.spade.oauth.feign.client.OAuthNaverClient;
import com.spade.oauth.property.NaverOAuthProperty;
import com.spade.oauth.property.OAuthProperty;
import feign.Feign;
import feign.codec.Encoder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
//@RequiredArgsConstructor
@DependsOn(value = {"encoder"})
//@Service
public class CommonOAuthService implements OAuthService {

    /**
     * Spring Encoder
     */
    private Encoder encoder;

    /**
     * naver 관련 property
     */
//    private final OAuthProperty oAuthProperty;

    /**
     * Request token 요청용 naver client
     */
//    private OAuthNaverClient oAuthNaverClient;

    private CommonOAuthClient commonOAuthClient;

    public CommonOAuthService(Encoder encoder, String requestTokenUrl) {
        this.encoder = encoder;
        this.commonOAuthClient = Feign.builder()
                                     .contract(new SpringMvcContract())
                                     .encoder(this.encoder)
                                     .target(CommonOAuthClient.class, requestTokenUrl);
    }

    /**
     * OAuth token 생성 요청
     */
    @Override
    public String requestAccessToken(ParamForAccessToken param) {
        String result = null;

        result = commonOAuthClient.requestAccessTokenCreate(param);
        if (result == null) {

            throw new AuthorizeFailureException("naver access token create fail");
        }

        return result;
    }

    /**
     * authorize 요청 url 생성
     */
//    @Override
//    public String createAuthorizeUrl() {
//        UriComponents url = UriComponentsBuilder.newInstance()
//                                                .path(oAuthProperty.getAuthorizeUrl())
//                                                .queryParam("response_type", "code")
//                                                .queryParam("client_id", oAuthProperty.getClientId())
//                                                .queryParam("redirect_uri", oAuthProperty.getCallBackHost() + oAuthProperty.getCallBackUri())
//                                                .queryParam("state", createRandomState())
//                                                .build();
//
//        return url.toString();
//    }

}