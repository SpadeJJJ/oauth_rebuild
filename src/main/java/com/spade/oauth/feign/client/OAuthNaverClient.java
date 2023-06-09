package com.spade.oauth.feign.client;

import com.spade.oauth.dto.param.ParamForAccessToken;
import feign.Headers;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Request token 요청용 naver client
 *
 * 확인 후 삭제 예정
 */
public interface OAuthNaverClient {
    @RequestMapping(method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/-www-form-urlencodexd;charset=utf-8")
    String requestAccessTokenCreate(ParamForAccessToken param);
}
