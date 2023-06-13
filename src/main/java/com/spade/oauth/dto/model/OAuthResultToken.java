package com.spade.oauth.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * OAuth 인증 요청 결과 관련 데이터 DTO
 *
 * result : 인증 요청 결과. (인증 토큰 등)
 * callbackUrl : CallBack 요청의 url
 * param : CallBack 요청의 파라미터
 * 참고 : localhost:8080/naver/callback?&code=1111&state=1111
 */
@Getter
@Setter
@AllArgsConstructor
public class OAuthResultToken {

    private String result;

    private String callbackUrl;

    private String param;
}
