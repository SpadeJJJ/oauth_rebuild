package com.spade.oauth.feign.client;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.feign.config.FeignConfig;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Naver OAuth 요청 Client
 * 삭제 예정
 */
//@FeignClient(value = "naver", url = "${oauth2.authorize-info.naver.host-url}", configuration = FeignConfig.class)
@FeignClient(value = "naver", url ="${oauth2.authorize-info.naver.host-url}",  configuration = FeignConfig.class)
public interface NaverOAuthClient {

//    @RequestMapping(value = "${oauth2.authorize-info.naver.authorize-url}", method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
//    @Headers("Content-Type: application/-www-form-urlencodexd;charset=utf-8")

    @RequestMapping(value = "${oauth2.authorize-info.naver.authorize-url}", method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
    String requestAccessTokenCreate(ParamForAccessToken token);

}
