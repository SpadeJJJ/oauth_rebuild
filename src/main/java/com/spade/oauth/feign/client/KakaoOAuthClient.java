package com.spade.oauth.feign.client;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.feign.config.FeignConfig;
import feign.Headers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Kakao OAuth 요청 Client
 * 삭제 예정
 */

//, url ="${oauth2.authorize-info.kakao.host-url}"
@FeignClient(value = "kakao", url = "${oauth2.authorize-info.kakao.host-url}",configuration = FeignConfig.class)

public interface KakaoOAuthClient {
//    @RequestMapping(value = "${oauth2.authorize-info.kakao.authorize-url}", method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
//    @Headers("Content-Type: application/-www-form-urlencodexd;charset=utf-8")

    @RequestMapping(value = "${oauth2.authorize-info.kakao.authorize-url}", method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
    String requestAccessTokenCreate(ParamForAccessToken token);
}
