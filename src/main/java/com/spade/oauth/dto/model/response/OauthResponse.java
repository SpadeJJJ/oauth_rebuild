package com.spade.oauth.dto.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OauthResponse {
    String accessToken;
    String refreshToken;
    String refreshTokenType;
    String expiresIn;
    String result;

}
