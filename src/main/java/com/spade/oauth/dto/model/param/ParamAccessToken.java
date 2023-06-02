package com.spade.oauth.dto.model.param;

import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;

// @feign.form.FormProperty("response_type")
@Setter
@Getter
public class ParamAccessToken {
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