package com.spade.oauth.feign.client;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Kakao OAuth 요청 Client
 */
@FeignClient(value = "kakao", url = "${oauth2.authorize-info.kakao.host-url}")
public interface KakaoOAuthClient {
    @RequestMapping(value = "${oauth2.authorize-info.kakao.authorize-url}", method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/-www-form-urlencodexd;charset=utf-8")
    String requestAccessTokenCreate(ParamForAccessToken token);
}
