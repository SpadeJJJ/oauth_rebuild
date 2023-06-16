package com.spade.oauth;

import com.spade.oauth.dto.model.param.ParamForAccessToken;
import com.spade.oauth.feign.config.FeignConfig;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(value = "aaaef", configuration = FeignConfig.class)
public interface KK {
//    @RequestMapping(value = "${oauth2.authorize-info.kakao.authorize-url}", method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
//    @Headers("Content-Type: application/-www-form-urlencodexd;charset=utf-8")

//    @RequestMapping(value = "${oauth2.authorize-info.naver.authorize-url}", method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
//    @Headers("Content-Type: application/-www-form-urlencodexd;charset=utf-8")
//    @RequestLine("GET /token")
//    @RequestMapping(method = GET)
//    @RequestLine("GET ")
    @RequestMapping(method=GET)
    String requestAccessTokenCreate();

    @RequestMapping(method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/-www-form-urlencodexd;charset=utf-8")
    String requestAccessTokenCreate2(ParamForAccessToken param);
}
