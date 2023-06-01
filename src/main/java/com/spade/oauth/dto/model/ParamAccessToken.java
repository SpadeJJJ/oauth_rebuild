package com.spade.oauth.dto.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;
import feign.form.FormProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ParamAccessToken {
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("client_secret")
    private String clientSecret;
//    @feign.form.FormProperty("grant_type")
//    @JsonProperty("grant_type")
//    @FormProperty("grant_type")

    @JsonProperty("grant_type")
    private String grantType;
    private String state;
    private String code;
    @JsonProperty("response_type")
    private String responseType;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("service_provider")
    private String serviceProvider;

    @JsonProperty("redirect_uri")
    private String redirectUri;

}
