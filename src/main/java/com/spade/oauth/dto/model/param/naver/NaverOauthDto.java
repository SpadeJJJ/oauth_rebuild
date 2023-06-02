package com.spade.oauth.dto.model.param.naver;

import lombok.Getter;

@Getter
public class NaverOauthDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private int expiresIn;
}
