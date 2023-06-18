package com.spade.oauth.dto.model.param;

import lombok.*;

/**
 * OAuth 인증 요청에 필요한 파라미터 정보 토큰
 * 삭제예정
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultParamForOAuthLoginRequest {

    private ResultParamForOAuthLoginRequest(String code) {
        this.code = code;
    }

    public ResultParamForOAuthLoginRequest(String state, String code) {
        this.state = state;
        this.code = code;
    }

    private String code;

    private String state;

    private String refreshToken;

    private String accessToken;
}
