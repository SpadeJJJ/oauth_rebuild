package com.spade.oauth.dto.model.param;

import lombok.*;

/**
 * OAuth 인증 요청에 필요한 파라미터 정보 토큰
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParamForOAuthAuthorizeRequest {

    private ParamForOAuthAuthorizeRequest(String code) {
        this.code = code;
    }

    public ParamForOAuthAuthorizeRequest(String state, String code) {
        this.state = state;
        this.code = code;
    }

    private String code;

    private String state;

    private String refreshToken;

    private String accessToken;
}
