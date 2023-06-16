package com.spade.oauth.dto.model.param;

import feign.form.FormProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * OAuth 로그인 요청 시에 필요한 파라미터 정보 토큰
 */
@Setter
@Getter
@NoArgsConstructor
public class ParamForAccessToken {
    @FormProperty("client_id")
    private String clientId;
    @FormProperty("client_secret")
    private String clientSecret;
    @FormProperty("grant_type")
    private String grantType;
    private String state;
    private String code;
    @FormProperty("response_type")
    private String responseType;
    @FormProperty("access_token")
    private String accessToken;
    @FormProperty("refresh_token")
    private String refreshToken;
    @FormProperty("service_provider")
    private String serviceProvider;
    @FormProperty("redirect_uri")
    private String redirectUri;

}